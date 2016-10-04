package com.juntcompany.godandgodsummer.Main.Toolbar.MyProfile;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.juntcompany.godandgodsummer.Main.MainActivity;
import com.juntcompany.godandgodsummer.Main.Toolbar.MyProfile.DefaultTab.LastWeekTargetFragment;
import com.juntcompany.godandgodsummer.Main.Toolbar.MyProfile.DefaultTab.SetMyTargetFragment;
import com.juntcompany.godandgodsummer.Main.Toolbar.MyProfile.NewsTab.NewsFragment;
import com.juntcompany.godandgodsummer.Main.Toolbar.MyProfile.ProfileTab.MyProfileFragment;
import com.juntcompany.godandgodsummer.Main.Toolbar.MyProfile.SettingTab.SettingFragment;
import com.juntcompany.godandgodsummer.Main.Toolbar.MyProfile.DefaultTab.TargetFragment;
import com.juntcompany.godandgodsummer.Manager.DataCache;
import com.juntcompany.godandgodsummer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class MyProfileMainFragment extends Fragment {
    Fragment f;

    public MyProfileMainFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_profile_main, container, false);
        //       툴바 셋팅
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        MainActivity activity = (MainActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
//        actionBar.set
        View viewToolbar = getActivity().getLayoutInflater().inflate(R.layout.toolbar_main_timeline, null);
        final EditText editSearch = (EditText)viewToolbar.findViewById(R.id.edit_search);
        editSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)getActivity()).getSearchFragment();
            }
        });
        Button btnToolbar = (Button)viewToolbar.findViewById(R.id.toolbar_btn_mark);
        btnToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).getToolbarFragment(MainActivity.FRAGMENT_MARKED);
            }
        });
        btnToolbar = (Button)viewToolbar.findViewById(R.id.toolbar_btn_friend);
        btnToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).getToolbarFragment(MainActivity.FRAGMENT_FRIEND);
            }
        });
        btnToolbar = (Button)viewToolbar.findViewById(R.id.toolbar_btn_profile);
        btnToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity) getActivity()).getToolbarFragment(MainActivity.FRAGMENT_PROFILE);
            }
        });
        actionBar.setCustomView(viewToolbar, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));
////////////////////        툴바셋팅

//        탭레이아웃 세팅
        final TabLayout tabLayout = (TabLayout)view.findViewById(R.id.tab_layout);

        f = new TargetFragment();

//        초기 화면용 프래그먼트  timeline 이 나와야함
        int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container_profile, f)
                .commit();

//        ViewPager viewPager = (ViewPager)view.findViewById(R.id.pager);
//        MyProfileViewPagerAdapter viewPagerAdapter = new MyProfileViewPagerAdapter(getChildFragmentManager());
//        viewPager.setAdapter(viewPagerAdapter);
//
//        tabLayout.setupWithViewPager(viewPager);
//
//        tabLayout.removeAllTabs();

        tabLayout.addTab(tabLayout.newTab().setText("내 프로필보기"));
        tabLayout.addTab(tabLayout.newTab().setText("소식전하기"));
        tabLayout.addTab(tabLayout.newTab().setText("환경설정"));

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimaryDark)); //초기 프래그먼트는 탭에 안속해서 포커스를 없애기 위해
                int position = tab.getPosition();
                Log.i("tab position", "" + position);
                switch (position){
                    case 0: {
                        f = new MyProfileFragment();
                        break;
                    }
                    case 1:{
                        f= new NewsFragment();
                        break;
                    }
                    case 2:{
                        f = new SettingFragment();
                        break;
                    }
                }
                getActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.container_profile, f)
                        .commit();
                //현재 fragment_my_profile_main.xml 에 있는  container_profile 에 띄워짐
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
    //               첫번째 프로필탭이 다시눌릴경우를 고려한 경우
                tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.colorPrimaryDark));
                switch (tab.getPosition()) {
                    case 0: {
                        f = new MyProfileFragment();
                        break;
                    }
                }
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.container_profile, f)
                            .commit();
//               첫번째 프로필탭이 다시눌릴경우를 고려한 경우
            }

        });

        return view;
    }

}
