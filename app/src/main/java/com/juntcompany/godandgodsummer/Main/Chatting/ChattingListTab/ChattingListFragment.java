package com.juntcompany.godandgodsummer.Main.Chatting.ChattingListTab;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.juntcompany.godandgodsummer.Chatting.ChattingActivity;
import com.juntcompany.godandgodsummer.Data.Chat;
import com.juntcompany.godandgodsummer.Data.NumberOfPeople;
import com.juntcompany.godandgodsummer.Manager.PropertyManager;
import com.juntcompany.godandgodsummer.R;




/**
 * A simple {@link Fragment} subclass.
 */
public class ChattingListFragment extends Fragment {
    private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference = firebaseDatabase.getReference();
    RecyclerView recyclerView;
    ChattingListAdapter mAdapter;
    String room_list;
    Button chat_bt;
    int count = 0;
    String email = PropertyManager.getInstance().getUserEmail().split("[.]")[0];

    /////

    public ChattingListFragment() {
        // Required empty public constructor
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_chatting_list, container, false);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        mAdapter = new ChattingListAdapter();
        mAdapter.setOnItemClickListener(new ChattingListAdapter.OnAdapterItemClickListener() {
            @Override
            public void onAdapterItemViewClick(View view, int position) {
                Chat chat = mAdapter.getItem(position);
                Intent intent = new Intent(getContext(), ChattingActivity.class);
                intent.putExtra("room_name", chat.room_name);
                intent.putExtra("email", email);
                startActivity(intent);
            }
            // 내가 추가한 부분 ///
            public void onAdapterItemViewLongClick(View view, final int position){
                Chat temp_item = mAdapter.getItem(position);
                temp_item.intent = null;
                databaseReference.child(email+temp_item.room_name).removeValue();
                databaseReference.child(temp_item.room_name).child(email).removeValue();
                mAdapter.getItem(position).number_of_persons = PropertyManager.getInstance().getRoomNOP(mAdapter.getItem(position).room_name);

                if(mAdapter.getItem(position).number_of_persons == 1) {
                    databaseReference.child(temp_item.room_name).removeValue();
                    databaseReference.child(temp_item.room_name+"number").removeValue();
                }

                String[] temp_list = room_list.split("[|]");
                room_list = "";
                for(int i=0; i<temp_list.length; i++){
                    if(!temp_list[i].equals(temp_item.room_name + "/" + temp_item.lastSpeak + "/" + temp_item.lastTime))
                        room_list += temp_list[i] + "|";
                }
                if(room_list.length() != 0)
                    room_list = room_list.substring(0, room_list.length()-1);
                Log.i("chat_longclick", "room : " + room_list );
                PropertyManager.getInstance().setRoomList(room_list);
                mAdapter.remove(position);
                count--;
            }
            /////
        });
        recyclerView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);

        chat_bt = (Button) view.findViewById(R.id.button_make_chatting_room);
        chat_bt.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                //상대방 찾기
                //방개설
                Chat chat = new Chat();
                chat.friendName = "사람";
                chat.lastSpeak = "사람이 말한 마지막 말";
                chat.lastTime = "시간";
                chat.number_of_persons = 1;
                chat.room_name = email + "room" + count;
                if(room_list.length() == 0)
                    room_list += chat.room_name + "/" + chat.lastSpeak + "/" + chat.lastTime;
                else
                    room_list += "|" + chat.room_name + "/" + chat.lastSpeak + "/" + chat.lastTime;

                Log.i("chat", "room : " + room_list  );
                PropertyManager.getInstance().setRoomList(room_list);

                databaseReference.child(chat.room_name).child(email).setValue(email);

                chat.intent = new Intent(getContext(), ChattingActivity.class);
                chat.intent.putExtra("room_name", chat.room_name);
                chat.intent.putExtra("email", email);
                mAdapter.add(chat);
                count++;

                //chat.intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                startActivity(chat.intent);
            }
        });

        recyclerView.setLayoutManager(layoutManager);

        initData();

        return view;
    }

    private void initData(){
        room_list = PropertyManager.getInstance().getRoomList();
        Log.i("chat_init", "room : " + room_list );
        if(room_list.length() != 0) {
            Toast.makeText(getContext(), "if" , Toast.LENGTH_SHORT).show();
            String[] rooms = room_list.split("[|]");
            for(int i=0; i<rooms.length; i++){
                String[] room_info = rooms[i].split("/");
                Chat chat = new Chat();
                chat.room_name = room_info[0];
                //chat.number_of_persons = Integer.parseInt(room_info[1]);
                chat.lastSpeak = room_info[1];
                chat.lastTime = room_info[2];
                mAdapter.add(chat);
                count++;
            }
        }
    }
}