package com.juntcompany.godandgodsummer.Main.Toolbar.MyProfile.DefaultTab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.juntcompany.godandgodsummer.Manager.PropertyManager;
import com.juntcompany.godandgodsummer.Manager.TargetManager;
import com.juntcompany.godandgodsummer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class TargetFragment extends Fragment {
    private ProgressBar faithgraph_progressbar;
    private ProgressBar populargraph_progressbar;
    private ProgressBar donategraph_progressbar;
    private ProgressBar friendlygraph_progressbar;

    private TextView target_faith_text;
    private TextView target_popular_text;
    private TextView target_donate_text;
    private TextView target_friendly_text;

    private TextView current_faith_text;
    private TextView current_popular_text;
    private TextView current_donate_text;
    private TextView current_friendly_text;

    private boolean flag = false;

    public TargetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_target, container, false);

        //sqlite 에 저장된 progressbar 값 가져오기
        final PropertyManager propertyManager = PropertyManager.getInstance();

        int current_target_faith = propertyManager.getCurrentTargetFaith();
        int current_target_popular = propertyManager.getCurrentTargetPopular();
        int current_target_donate = propertyManager.getCurrentTargetDonate();
        int current_target_friendly = propertyManager.getCurrentTargetFriendly();


//       프로필 내용에 들어가는 프로그래스바 및 텍스트 세팅 //content_my_propfile_status 세팅
        faithgraph_progressbar = (ProgressBar)view.findViewById(R.id.faithgraph_progressbar);
        populargraph_progressbar = (ProgressBar)view.findViewById(R.id.populargraph_progressbar);
        donategraph_progressbar = (ProgressBar)view.findViewById(R.id.donategraph_progressbar);
        friendlygraph_progressbar = (ProgressBar)view.findViewById(R.id.friendlygraph_progressbar);

        target_faith_text =(TextView)view.findViewById(R.id.target_faith_text);
        target_popular_text =(TextView)view.findViewById(R.id.target_popular_text);
        target_donate_text =(TextView)view.findViewById(R.id.target_donate_text);
        target_friendly_text =(TextView)view.findViewById(R.id.target_friendly_text);

        current_faith_text =(TextView)view.findViewById(R.id.current_faith_text);
        current_popular_text =(TextView)view.findViewById(R.id.current_popular_text);
        current_donate_text =(TextView)view.findViewById(R.id.current_donate_text);
        current_friendly_text =(TextView)view.findViewById(R.id.current_friendly_text);

        target_faith_text.setText(Integer.toString(current_target_faith));
        target_popular_text.setText(Integer.toString(current_target_popular));
        target_donate_text.setText(Integer.toString(current_target_donate));
        target_friendly_text.setText(Integer.toString(current_target_friendly));

        current_faith_text.setText(Integer.toString(10));
        current_popular_text.setText(Integer.toString(10));
        current_donate_text.setText(Integer.toString(10));
        current_friendly_text.setText(Integer.toString(10));

        if(current_target_faith != 0)
            faithgraph_progressbar.setProgress(Integer.parseInt(current_faith_text.getText().toString()) * 100 / Integer.parseInt(target_faith_text.getText().toString()));
        else
            faithgraph_progressbar.setProgress(0);
        if(current_target_popular != 0)
            populargraph_progressbar.setProgress(Integer.parseInt(current_popular_text.getText().toString()) * 100 / Integer.parseInt(target_popular_text.getText().toString()));
        else
            populargraph_progressbar.setProgress(0);
        if(current_target_donate != 0)
            donategraph_progressbar.setProgress(Integer.parseInt(current_donate_text.getText().toString()) * 100 / Integer.parseInt(target_donate_text.getText().toString()));
        else
            donategraph_progressbar.setProgress(0);
        if(current_target_friendly != 0)
            friendlygraph_progressbar.setProgress(Integer.parseInt(current_friendly_text.getText().toString()) * 100 / Integer.parseInt(target_friendly_text.getText().toString()));
        else
            friendlygraph_progressbar.setProgress(0);

//               프로필 내용에 들어가는 프로그래스바 및 텍스트 세팅

        Button btn = (Button)view.findViewById(R.id.button_last_target); //지난주 목표
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();
                Log.i("backstack", "backstack count" + count);
                Fragment f = new LastWeekTargetFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, f); //메인의 container 에 프래그먼트가 대체됨
                ft.addToBackStack(""+count);
                ft.commit();
            }
        });
        btn = (Button)view.findViewById(R.id.button_target_settings); //목표 설정
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();
                Log.i("backstack", "backstack count" + count);
                Fragment f = new SetMyTargetFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.container, f); //메인의 container 에 프래그먼트가 대체됨
                ft.addToBackStack(""+count);
                ft.commit();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onActivityCreated(savedInstanceState);
    }
}