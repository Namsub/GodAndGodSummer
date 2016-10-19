package com.juntcompany.godandgodsummer.Main.Toolbar.MyProfile.DefaultTab;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.juntcompany.godandgodsummer.Main.MainActivity;
import com.juntcompany.godandgodsummer.Manager.PropertyManager;
import com.juntcompany.godandgodsummer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class LastWeekTargetFragment extends Fragment {
    private TextView text_faith_target;
    private TextView text_popular_target;
    private TextView text_donate_target;
    private TextView text_friendly_target;
    private TextView text_faith_figure;
    private TextView text_popular_figure;
    private TextView text_donate_figure;
    private TextView text_friendly_figure;
    private ProgressBar faithgraph_progressbar;
    private ProgressBar populargraph_progressbar;
    private ProgressBar donategraph_progressbar;
    private ProgressBar friendlygraph_progressbar;

    public LastWeekTargetFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true); //꼭 해야 homebutton 이 동작함
    }
    private void setObjects(View view){
        //        신앙도, 인기도, 봉헌도, 친밀도 editText 및 progressbar 객체 가져오기
        text_faith_target = (TextView)view.findViewById(R.id.text_faith_target);
        text_popular_target = (TextView)view.findViewById(R.id.text_popular_target);
        text_donate_target = (TextView)view.findViewById(R.id.text_donate_target);
        text_friendly_target = (TextView)view.findViewById(R.id.text_friendly_target);

        text_faith_figure = (TextView)view.findViewById(R.id.text_faith_figure);
        text_popular_figure = (TextView)view.findViewById(R.id.text_popular_figure);
        text_donate_figure = (TextView)view.findViewById(R.id.text_donate_figure);
        text_friendly_figure = (TextView)view.findViewById(R.id.text_friendly_figure);

        faithgraph_progressbar = (ProgressBar) view.findViewById(R.id.faithgraph_progressbar);
        populargraph_progressbar = (ProgressBar) view.findViewById(R.id.populargraph_progressbar);
        donategraph_progressbar = (ProgressBar) view.findViewById(R.id.donategraph_progressbar);
        friendlygraph_progressbar = (ProgressBar) view.findViewById(R.id.friendlygraph_progressbar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_last_week_target, container, false);

        //        툴바 세팅

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        MainActivity activity = (MainActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.button_back);
//        actionBar.set
        View viewToolbar = getActivity().getLayoutInflater().inflate(R.layout.toolbar_profile_check, null);
        Button btn = (Button)viewToolbar.findViewById(R.id.button_check);
        btn.setVisibility(View.GONE);
        actionBar.setCustomView(viewToolbar, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

//        툴바 세팅

        setObjects(view);

        // 로컬 DB에서 지난주 target값 및 달성값 가져오기
        PropertyManager propertyManager = PropertyManager.getInstance();

        text_faith_target.setText(Integer.toString(propertyManager.getPreviousTargetFaith()));
        text_popular_target.setText(Integer.toString(propertyManager.getPreviousTargetPopular()));
        text_donate_target.setText(Integer.toString(propertyManager.getPreviousTargetDonate()));
        text_friendly_target.setText(Integer.toString(propertyManager.getPreviousTargetFriendly()));

        text_faith_figure.setText(Integer.toString(10));
        text_popular_figure.setText(Integer.toString(20));
        text_donate_figure.setText(Integer.toString(30));
        text_friendly_figure.setText(Integer.toString(40));
        //

        //progressbar에 표현하기(일단 이렇게 구현. 나중에 DB에서 가져온 값 그대로 적용하여 연산 줄일 예정)
        if(Integer.parseInt(text_faith_target.getText().toString()) != 0)
            faithgraph_progressbar.setProgress(Integer.parseInt(text_faith_figure.getText().toString()) * 100 / Integer.parseInt(text_faith_target.getText().toString()));
        else
            faithgraph_progressbar.setProgress(0);
        if(Integer.parseInt(text_popular_target.getText().toString()) != 0)
            populargraph_progressbar.setProgress(Integer.parseInt(text_popular_figure.getText().toString()) * 100 / Integer.parseInt(text_popular_target.getText().toString()));
        else
            populargraph_progressbar.setProgress(0);
        if(Integer.parseInt(text_donate_target.getText().toString()) != 0)
            donategraph_progressbar.setProgress(Integer.parseInt(text_donate_figure.getText().toString()) * 100 / Integer.parseInt(text_donate_target.getText().toString()));
        else
            donategraph_progressbar.setProgress(0);
        if(Integer.parseInt(text_friendly_target.getText().toString()) != 0)
            friendlygraph_progressbar.setProgress(Integer.parseInt(text_friendly_figure.getText().toString()) * 100 / Integer.parseInt(text_friendly_target.getText().toString()));
        else
            friendlygraph_progressbar.setProgress(0);
        return view;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                getActivity().getSupportFragmentManager().popBackStack();

                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }




}
