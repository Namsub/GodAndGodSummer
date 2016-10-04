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

    private TextView faithgraph_text;
    private TextView populargraph_text;
    private TextView donategraph_text;
    private TextView friendlygraph_text;

    public TargetFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_target, container, false);

        //sqlite 에 저장된 progressbar 값 가져오기
        final TargetManager targetmanager = TargetManager.getInstance();

        int[] result;
        try {
            result = targetmanager.select();
            targetmanager.delete(1);
            //Toast.makeText(getContext(), Integer.toString(result[0]), Toast.LENGTH_SHORT).show();
        } catch(Exception e){
            //Toast.makeText(getContext(), "Exception", Toast.LENGTH_SHORT).show();
            result = new int[4];
            result[0] = 0;
            result[1] = 0;
            result[2] = 0;
            result[3] = 0;
        } finally{
        }

//       프로필 내용에 들어가는 프로그래스바 및 텍스트 세팅 //content_my_propfile_status 세팅
        faithgraph_progressbar = (ProgressBar)view.findViewById(R.id.faithgraph_progressbar);
        populargraph_progressbar = (ProgressBar)view.findViewById(R.id.populargraph_progressbar);
        donategraph_progressbar = (ProgressBar)view.findViewById(R.id.donategraph_progressbar);
        friendlygraph_progressbar = (ProgressBar)view.findViewById(R.id.friendlygraph_progressbar);

        faithgraph_text =(TextView)view.findViewById(R.id.faithgraph_text);
        populargraph_text =(TextView)view.findViewById(R.id.populargraph_text);
        donategraph_text =(TextView)view.findViewById(R.id.donategraph_text);
        friendlygraph_text =(TextView)view.findViewById(R.id.friendlygraph_text);

        faithgraph_progressbar.setProgress(result[0]);
        faithgraph_text.setText(String.valueOf(faithgraph_progressbar.getProgress()));
        populargraph_progressbar.setProgress(result[1]);
        populargraph_text.setText(String.valueOf(populargraph_progressbar.getProgress()));
        donategraph_progressbar.setProgress(result[2]);
        donategraph_text.setText(String.valueOf(donategraph_progressbar.getProgress()));
        friendlygraph_progressbar.setProgress(result[3]);
        friendlygraph_text.setText(String.valueOf(friendlygraph_progressbar.getProgress()));
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