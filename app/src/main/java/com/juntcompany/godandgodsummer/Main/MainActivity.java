package com.juntcompany.godandgodsummer.Main;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.juntcompany.godandgodsummer.Main.Activity.ActivityLogFragment;
import com.juntcompany.godandgodsummer.Main.Chatting.ChattingManageFragment;
import com.juntcompany.godandgodsummer.Main.Toolbar.Search.SearchFragment;
import com.juntcompany.godandgodsummer.Main.TimeLine.TimelineFragment;
import com.juntcompany.godandgodsummer.Main.Toolbar.FriendManage.FriendManageFragment;
import com.juntcompany.godandgodsummer.Main.Toolbar.Marked.MarkedFragment;
import com.juntcompany.godandgodsummer.Main.Toolbar.MyProfile.MyProfileMainFragment;
import com.juntcompany.godandgodsummer.Main.Video.VideoFragment;
import com.juntcompany.godandgodsummer.Manager.PropertyManager;
import com.juntcompany.godandgodsummer.R;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    boolean isBackPressed = false;
    public static final int MESSAGE_BACK_KEY_TIMEOUT = 0;
    public static final int BACK_KEY_TIME = 2000;
    Handler mHandler = new Handler(Looper.getMainLooper(), new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            switch (msg.what) {
                case MESSAGE_BACK_KEY_TIMEOUT :
                    isBackPressed = false;
                    return true;
            }
            return false;
        }
    });
//    백프레스 종료 용


    TabLayout tabLayout;
    Fragment f; //탭에 나오는 프래그먼트 용
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //String token = FirebaseInstanceId.getInstance().getToken();
        //Log.d("MainActivity", "Refreshed token: " + token);
// 메인화면 띄워질 때 제일 먼저 마지막 접속시간 비교해서 지난주 타겟값 업데이트하기
        //마지막 접속시간 불러오기
        PropertyManager propertymanager = PropertyManager.getInstance();
        String time = propertymanager.getLastAccessTime();

        int previous_year = 0;
        int previous_week = 0;
        if(!time.isEmpty()) {
            String[] split_time = time.split("/");

            previous_year = Integer.parseInt(split_time[0]);
            previous_week = Integer.parseInt(split_time[1]);
        }

 //       getInstanceIdToken();
        //현재시간 불러오기
        Calendar current_cal = Calendar.getInstance();
        int current_year = current_cal.get(Calendar.YEAR);
        int current_week = current_cal.get(Calendar.WEEK_OF_YEAR);
        propertymanager.setLastAccessTime(Integer.toString(current_year) + "/" + Integer.toString(current_week));

        boolean update = false;

        Log.i("Calendar", "" + current_year + previous_year + current_week + previous_week);
        if(current_year == previous_year){
            if(current_week == previous_week+1)
                update = true;
        }
        else if(current_year > previous_year){ //여기서 이슈는 똑같은 주에 년도가 바뀌는 경우에 대해서 처리해야함(아래코드는 해당 경우의수 반영 못함)
            Calendar cal = Calendar.getInstance();
            cal.set(Calendar.YEAR, current_year-1);
            cal.set(Calendar.MONTH, 12);
            cal.set(Calendar.DATE, 31);
            current_week += cal.get(Calendar.WEEK_OF_YEAR);
            if(current_week == previous_week+1)
                update = true;
        }

        //update가 true 일때만 지난 주 target 값 업데이트 및 이번주 target 값 초기화
        if(update == true){
            propertymanager.setPreviousTargetFaith(propertymanager.getCurrentTargetFaith());
            propertymanager.setPreviousTargetPopular(propertymanager.getCurrentTargetPopular());
            propertymanager.setPreviousTargetDonate(propertymanager.getCurrentTargetDonate());
            propertymanager.setPreviousTargetFriendly(propertymanager.getCurrentTargetFriendly());
        }

//
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//
//        setSupportActionBar(toolbar);
//final         ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowCustomEnabled(true);
//        actionBar.setDisplayShowTitleEnabled(false);
////        actionBar.set
//        View viewToolbar = getLayoutInflater().inflate(R.layout.toolbar_main_timeline, null);
//        EditText editSearch = (EditText)viewToolbar.findViewById(R.id.edit_search);
//        editSearch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                actionBar.setDisplayHomeAsUpEnabled(true);
//                actionBar.setHomeAsUpIndicator(R.drawable.button_back);
//            }
//        });
//        Button btn = (Button)viewToolbar.findViewById(R.id.toolbar_btn_mark);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getToolbarFragment(MainActivity.FRAGMENT_MARKED);
//            }
//        });
//        btn = (Button)viewToolbar.findViewById(R.id.toolbar_btn_friend);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getToolbarFragment(FRAGMENT_FRIEND);
//            }
//        });
//        btn = (Button)viewToolbar.findViewById(R.id.toolbar_btn_profile);
//        btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                getToolbarFragment(FRAGMENT_PROFILE);
//            }
//        });
//        actionBar.setCustomView(viewToolbar, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
////        툴바 세팅


        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab_timeline));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab_chat));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab_video));
        tabLayout.addTab(tabLayout.newTab().setIcon(R.drawable.tab_notice));

//
        switch(PropertyManager.getInstance().getInitialFragmentOfMainActivity()){
            case 0:{
                f = new TimelineFragment();
                break;
            }
            case 1:{
                f= new ChattingManageFragment();
                tabLayout.getTabAt(1).select();
                PropertyManager.getInstance().setInitialFragmentOfMainActivity(0);
                break;
            }
        }
//        초기 화면용 프래그먼트  timeline 이 나와야함
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, f)
                .commit();

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                Log.i("tab position", "" + position);
                switch (position){
                    case 0: {
                        f = new TimelineFragment();
                        break;
                    }
                    case 1:{
                        f= new ChattingManageFragment();
                        break;
                    }
                    case 2:{
                        f = new VideoFragment();
                        break;
                    }
                    case 3:{
                        f = new ActivityLogFragment();
                        break;
                    }
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, f)
                        .commit();
//                content_main.xml 에 있는 container 에 프래그먼트가 띄워짐
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) { //툴바에 있는 프래그먼트도 사용하므로 탭이 다시 눌린경우도 고려해야함
                int position = tab.getPosition();
                Log.i("tab position", "" + position);
                switch (position){
                    case 0: {
                        f = new TimelineFragment();
                        break;
                    }
                    case 1:{
                        f= new ChattingManageFragment();
                        break;
                    }
                    case 2:{
                        f = new VideoFragment();
                        break;
                    }
                    case 3:{
                        f = new ActivityLogFragment();
                        break;
                    }
                }
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container, f)
                        .commit();
//                content_main.xml 에 있는 container 에 프래그먼트가 띄워짐

            }
        });


    }

    public static final String FRAGMENT_MARKED = "marked";
    public static final String FRAGMENT_FRIEND = "friend";
    public static final String FRAGMENT_PROFILE = "profile";
//    탭에 있는 툴바에서 프래그먼트를 띄우기 위해 만듬, 프래그 먼트용 메소드
    public void getToolbarFragment(String message){

        if(message.equals(FRAGMENT_MARKED)){
            f = new MarkedFragment();
        }else if(message.equals(FRAGMENT_FRIEND)){
            f = new FriendManageFragment();
        }else if(message.equals(FRAGMENT_PROFILE)){
            f= new MyProfileMainFragment();
        }
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, f)
                .commit();
    }

    public void getSearchFragment(){
        Fragment f = new SearchFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container,f)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void onBackPressed() {
        if(!isBackPressed){
            Toast.makeText(this, "뒤로 버튼을 한번 더 누르시면 종료됩니다", Toast.LENGTH_SHORT).show();
            isBackPressed = true;
            mHandler.sendEmptyMessageDelayed(MESSAGE_BACK_KEY_TIMEOUT, BACK_KEY_TIME);
        }else {
            mHandler.removeMessages(MESSAGE_BACK_KEY_TIMEOUT);
            finish();
        }
    }


}
