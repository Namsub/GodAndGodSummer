package com.juntcompany.godandgodsummer.Chatting;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.juntcompany.godandgodsummer.Data.Chat;
import com.juntcompany.godandgodsummer.Data.Message;
import com.juntcompany.godandgodsummer.Data.MessageFire;
import com.juntcompany.godandgodsummer.Data.NumberOfPeople;
import com.juntcompany.godandgodsummer.Data.User;
import com.juntcompany.godandgodsummer.Main.MainActivity;
import com.juntcompany.godandgodsummer.Manager.PropertyManager;
import com.juntcompany.godandgodsummer.R;
import com.juntcompany.godandgodsummer.Util.Rest.ApiClient;
import com.juntcompany.godandgodsummer.Util.Rest.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirebaseChattingFragment extends Fragment {


    public FirebaseChattingFragment() {
        // Required empty public constructor
    }
    ChildEventListener room_name_listener;
    ValueEventListener room_people_number_listener;
    private int people_count = 1;
    private int server_people_count = 0;
    private int flag = 0;
    //NumberOfPeople nop = new NumberOfPeople();
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    EditText editInput;
    RecyclerView recyclerView;
    MessageAdapter mAdapter;
//    private List<Message> messageList = new ArrayList<Message>();
    private List<MessageFire> messageFireList = new ArrayList<MessageFire>();
    private List<String> opponents = new ArrayList<String>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_firebase_chatting, container, false);
        ////////////// 방 이름 받아오기
        Intent intent = getActivity().getIntent();
        final String room_name = (String)intent.getSerializableExtra("room_name");
        final String email = (String)intent.getSerializableExtra("email");
        ChattingActivity ch = ((ChattingActivity) getActivity());
        ch.current_room_name = room_name;
        ch.email = email;

        //////////////리사이클러뷰 세팅
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        mAdapter = new MessageAdapter(messageFireList);

        recyclerView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        scrollToBottom();

/////////////////////////////리사이클러뷰 세팅
        editInput = (EditText)view.findViewById(R.id.edit_input);
        Button btn = (Button)view.findViewById(R.id.btn_send);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = editInput.getText().toString().trim();

                if(TextUtils.isEmpty(content)){ //메시지가 비었으면 아무것도 아님
                    return;
                }
                editInput.setText("");

                Message myself_chatData = new Message.Builder(Message.TYPE_MESSAGE_SEND).username("").message(content).build();
                databaseReference.child(email+room_name).push().setValue(myself_chatData); //메시지
                for(int i=0; i<opponents.size(); i++) {
                    Message opponent_chatData = new Message.Builder(Message.TYPE_MESSAGE_RECEIVE).username(email).message(content).build();
                    databaseReference.child(opponents.get(i) + room_name).push().setValue(opponent_chatData); //메시지
                } //최적화 작업 필요

            }
        });
        btn = (Button)view.findViewById(R.id.button_test);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String friendId = editInput.getText().toString().trim();
                if(TextUtils.isEmpty(friendId)){ //메시지가 비었으면 아무것도 아님
                    return;
                }
                editInput.setText("");

                ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                Call<Chat> call = apiInterface.inviteFriend(room_name, friendId);
                call.enqueue(new Callback<Chat>() {
                    @Override
                    public void onResponse(Call<Chat> call, Response<Chat> response) {
                        Log.i("test", response.body().toString() + " : " + response.body().result.message + response.body().result.status);
                        if(response.body().result.status.equals("fail")){
                            Toast.makeText(getContext(), response.body().result.message, Toast.LENGTH_SHORT).show();
                        } else if (response.body().result.status.equals("success")) {
                            databaseReference.child(room_name+"number").setValue(Integer.toString(people_count+1));
                            flag = 1;
                        }
                    }
                    @Override
                    public void onFailure(Call<Chat> call, Throwable t) {
                    }
                });
            }
        });

        databaseReference.child(email+room_name).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MessageFire message = dataSnapshot.getValue(MessageFire.class);
                Log.i("fire", "message : " + message.message  );
                messageFireList.add(message);
                mAdapter.notifyItemInserted(messageFireList.size() -1);
                scrollToBottom();
            }
            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        room_people_number_listener = databaseReference.child(room_name+"number").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String number = dataSnapshot.getValue(String.class);
                if(number != null)
                    server_people_count = Integer.parseInt(number);
                Log.i("fire_event", " room_number: " + server_people_count);
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
        room_name_listener = databaseReference.child(room_name).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String add_user = dataSnapshot.getValue(String.class);
                if(!add_user.equals(email)) {
                    people_count += 1;
                    opponents.add(add_user);
                    if(flag != 0) {
                        Message chatData = new Message.Builder(Message.TYPE_MESSAGE_RECEIVE).username(add_user).message(" 님이 접속하였습니다.").build();
                        databaseReference.child(email + room_name).push().setValue(chatData); //메시지
                    }
                    if(server_people_count == people_count)
                        flag = 1;

                    PropertyManager.getInstance().setRoomNOP(room_name, people_count);
                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String remove_user = dataSnapshot.getValue(String.class);
                Log.i("fire", "people_number : " + people_count);
                if(remove_user.equals(email)) {
                    //databaseReference.child(room_name).removeEventListener(room_name_listener);
                }
                else{
                    Message chatData = new Message.Builder(Message.TYPE_MESSAGE_RECEIVE).username(remove_user).message(" 님이 퇴장하셨습니다.").build();
                    databaseReference.child(email+room_name).push().setValue(chatData); //메시지
                    opponents.remove(remove_user);
                    people_count -= 1;
                    databaseReference.child(room_name+"number").setValue(Integer.toString(people_count));
                    PropertyManager.getInstance().setRoomNOP(room_name, people_count);
                }
            }
            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
//        initData();

        return view;
    }

    private void scrollToBottom() {  // 리스트를 가장 밑으로 가게 하는 메소드
        recyclerView.scrollToPosition(mAdapter.getItemCount() - 1);
    }
    public void removeFirebaseEventListener(String room_name){
        databaseReference.child(room_name).removeEventListener(room_name_listener);
        databaseReference.child(room_name+"number").removeEventListener(room_people_number_listener);
    }


    public void initData(){
        Message message = new Message();
        Message message1 = new Message.Builder(Message.TYPE_MESSAGE_SEND).username("ddd").message("ddd").build();
        message = message1;
        mAdapter.add(message);
    }
}
