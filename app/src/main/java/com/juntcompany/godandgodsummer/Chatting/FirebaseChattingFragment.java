package com.juntcompany.godandgodsummer.Chatting;


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
import com.juntcompany.godandgodsummer.Data.Message;
import com.juntcompany.godandgodsummer.Data.MessageFire;
import com.juntcompany.godandgodsummer.Data.NumberOfPeople;
import com.juntcompany.godandgodsummer.Manager.PropertyManager;
import com.juntcompany.godandgodsummer.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FirebaseChattingFragment extends Fragment {


    public FirebaseChattingFragment() {
        // Required empty public constructor
    }
    private int people_count = 1;
    NumberOfPeople nop = new NumberOfPeople();
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
        View view = inflater.inflate(R.layout.fragment_firebase_chatting, container, false);

        ////////////// 방 번호 받아오기
        Intent intent = getActivity().getIntent();
        final String room_number = (String)intent.getSerializableExtra("room_number");
        final String email = (String)intent.getSerializableExtra("email");


        //////////////리사이클러뷰 세팅
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        mAdapter = new MessageAdapter(messageFireList);

        recyclerView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        scrollToBottom();

/////////////////////////////리사이클러뷰 세팅
        //databaseReference.child(room_number).push().setValue(people_count); //메시지
        Toast.makeText(getContext(), people_count+email+room_number , Toast.LENGTH_SHORT).show();

        editInput = (EditText)view.findViewById(R.id.edit_input);
        Button btn = (Button)view.findViewById(R.id.btn_send);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = "juntae";
                String content = editInput.getText().toString().trim();
                Message chatData = new Message.Builder(Message.TYPE_MESSAGE_SEND).username(username).message(content).build();

                if(TextUtils.isEmpty(content)){ //메시지가 비었으면 아무것도 아님
                    return;
                }
                editInput.setText("");
                databaseReference.child(email+room_number).push().setValue(chatData); //메시지
                for(int i=1; i<people_count; i++)
                    databaseReference.child(opponents.get(i-1)+room_number).push().setValue(chatData); //메시지

            }
        });
        btn = (Button)view.findViewById(R.id.button_test);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String username = "hayun";
                String content = editInput.getText().toString().trim();
                //Message chatData = new Message.Builder(Message.TYPE_MESSAGE_RECEIVE).username(username).message(content).build();
                if(TextUtils.isEmpty(content)){ //메시지가 비었으면 아무것도 아님
                    return;
                }
                editInput.setText("");
                //databaseReference.child(room_number).push().setValue(content);
                databaseReference.child(room_number).child(content).setValue(content);
//                people_count += 1;
//                //databaseReference.child(room_number).setValue(people_count); //푸쉬기능 구현되면 주석풀기
//                opponents.add(content);
//                Log.i("firebase", "check_people_count : " + people_count);
            }
        });

        databaseReference.child(email+room_number).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                MessageFire message = dataSnapshot.getValue(MessageFire.class);
//                Message message1 =dataSnapshot.getValue(Message.class);
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
        databaseReference.child(room_number).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String add_user = dataSnapshot.getValue(String.class);
                Log.i("fire", "email : " + email);
                if(!add_user.equals(email)) {
                    Message chatData = new Message.Builder(Message.TYPE_MESSAGE_SEND).username(add_user).message(" 님이 접속하였습니다.").build();
                    databaseReference.child(email+room_number).push().setValue(chatData); //메시지
                    people_count += 1;
                    opponents.add(add_user);
                    PropertyManager.getInstance().setRoomNOP(room_number, people_count);
                }
                Log.i("fire", "add_user : " + add_user);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
//                    int nop = dataSnapshot.getValue(int.class);
//                    Log.i("fire", "messagge : " + nop);
//                    //people_count = nop;   //푸쉬기능 구현되면 주석풀기
//                    PropertyManager.getInstance().setRoomNOP(room_number, people_count);
            }
            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String remove_user = dataSnapshot.getValue(String.class);
                if(!remove_user.equals(email)) {
                    Message chatData = new Message.Builder(Message.TYPE_MESSAGE_SEND).username(remove_user).message(" 님이 퇴장하셨습니다.").build();
                    databaseReference.child(email+room_number).push().setValue(chatData); //메시지
                    opponents.remove(remove_user);
                }
                people_count -= 1;
                PropertyManager.getInstance().setRoomNOP(room_number, people_count);
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


    public void initData(){
        Message message = new Message();
        Message message1 = new Message.Builder(Message.TYPE_MESSAGE_SEND).username("ddd").message("ddd").build();
        message = message1;
        mAdapter.add(message);
    }

}
