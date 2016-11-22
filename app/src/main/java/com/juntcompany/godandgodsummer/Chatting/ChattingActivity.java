package com.juntcompany.godandgodsummer.Chatting;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.FirebaseDatabase;
import com.juntcompany.godandgodsummer.Main.MainActivity;
import com.juntcompany.godandgodsummer.Manager.PropertyManager;
import com.juntcompany.godandgodsummer.R;

import com.github.nkzawa.socketio.client.IO;
import com.github.nkzawa.socketio.client.Socket;
import com.juntcompany.godandgodsummer.Util.GodAndGod;



import java.net.URISyntaxException;

public class ChattingActivity extends AppCompatActivity {
    public String current_room_name = "";
    public String email = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);


//        툴바 세팅
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Get the ActionBar here to configure the way it behaves.
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.button_back);

        View viewToolbar =getLayoutInflater().inflate(R.layout.toolbar_chatting, null);
        TextView textToolbarChatting = (TextView)viewToolbar.findViewById(R.id.text_toolbar_title);

        Button btn = (Button)viewToolbar.findViewById(R.id.button_setting);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        actionBar.setCustomView(viewToolbar, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
////////////////////        툴바셋팅

//        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                for(Fragment fragment: getSupportFragmentManager().getFragments()){
                    if(fragment.isVisible()){
                        ((FirebaseChattingFragment)fragment).removeFirebaseEventListener(current_room_name);
                    }
                }//현재 보여지고 있는 가장 최상위 fragment 가져오기
                finish();
            }
        }
        PropertyManager.getInstance().setInitialFragmentOfMainActivity(1);
        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // 메인을 들어가면 메인 전에 실행했던 TASK 를 모두 삭제
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }
}
