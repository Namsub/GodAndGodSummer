package com.juntcompany.godandgodsummer.Main.Toolbar.MyProfile.DefaultTab;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.juntcompany.godandgodsummer.Main.MainActivity;
import com.juntcompany.godandgodsummer.Manager.TargetManager;
import com.juntcompany.godandgodsummer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SetMyTargetFragment extends Fragment {
    private View view;
    private EditText edit_faith;
    private EditText edit_popular;
    private EditText edit_donate;
    private EditText edit_friendly;
    private ProgressBar faithgraph_progressbar;
    private ProgressBar populargraph_progressbar;
    private ProgressBar donategraph_progressbar;
    private ProgressBar friendlygraph_progressbar;

    public SetMyTargetFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);  //꼭 해야 homebutton 이 동작함
    }

    private void setObjects(View view){
        //        신앙도, 인기도, 봉헌도, 친밀도 editText 및 progressbar 객체 가져오기
        edit_faith = (EditText)view.findViewById(R.id.edit_faith_target);
        edit_popular = (EditText)view.findViewById(R.id.edit_popular_target);
        edit_donate = (EditText)view.findViewById(R.id.edit_donate_target);
        edit_friendly = (EditText)view.findViewById(R.id.edit_friendly_target);
        faithgraph_progressbar = (ProgressBar) view.findViewById(R.id.faithgraph_progressbar);
        populargraph_progressbar = (ProgressBar) view.findViewById(R.id.populargraph_progressbar);
        donategraph_progressbar = (ProgressBar) view.findViewById(R.id.donategraph_progressbar);
        friendlygraph_progressbar = (ProgressBar) view.findViewById(R.id.friendlygraph_progressbar);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.i("lifeFragment", "oncreatedView");
        view = inflater.inflate(R.layout.fragment_set_my_target, container, false);

        setObjects(view);

///////////////////        툴바 세팅

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        MainActivity activity = (MainActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.button_back);
/////////////////////        actionBar.set


        View viewToolbar = getActivity().getLayoutInflater().inflate(R.layout.toolbar_profile_check, null);
        Button btn = (Button)viewToolbar.findViewById(R.id.button_check);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // sqlite 에 각 progressbar 값들 저장
                TargetManager targetmanager = TargetManager.getInstance();
                targetmanager.insert(faithgraph_progressbar.getProgress(), populargraph_progressbar.getProgress(), donategraph_progressbar.getProgress(), friendlygraph_progressbar.getProgress());
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        actionBar.setCustomView(viewToolbar, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

//////////////////////////////////        툴바 세팅

        // 신앙도 위젯 부분
        edit_faith.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if(count != 0) {
                    try {
                        faithgraph_progressbar.setProgress(Integer.parseInt(charSequence.toString()));
                    } catch(Exception e){
                        Toast.makeText(getContext(), "숫자를 입력해주세요", Toast.LENGTH_SHORT).show();
                    } finally {
                    }
                }
                    //donategraph_progressbar.setVisibility(ProgressBar.VISIBLE);
                else
                    faithgraph_progressbar.setProgress(0);
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // 인기도 위젯 부분
        edit_popular.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if(count != 0) {
                    try {
                        populargraph_progressbar.setProgress(Integer.parseInt(charSequence.toString()));
                    } catch(Exception e){
                        Toast.makeText(getContext(), "숫자를 입력해주세요", Toast.LENGTH_SHORT).show();
                    } finally {
                    }
                }
                else
                    populargraph_progressbar.setProgress(0);
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // 봉헌도 위젯 부분
        edit_donate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if(count != 0) {
                    try {
                        donategraph_progressbar.setProgress(Integer.parseInt(charSequence.toString()));
                    } catch(Exception e){
                        Toast.makeText(getContext(), "숫자를 입력해주세요", Toast.LENGTH_SHORT).show();
                    } finally {
                    }
                }
                    //donategraph_progressbar.setVisibility(ProgressBar.VISIBLE);
                else
                    donategraph_progressbar.setProgress(0);
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        // 친밀도 위젯 부분
        edit_friendly.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {
                if(count != 0) {
                    try {
                        friendlygraph_progressbar.setProgress(Integer.parseInt(charSequence.toString()));
                    } catch(Exception e){
                        Toast.makeText(getContext(), "숫자를 입력해주세요", Toast.LENGTH_SHORT).show();
                    } finally {
                    }
                }
                else
                    friendlygraph_progressbar.setProgress(0);
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


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

//    @Override
//    public void onDetach() {
//        super.onDetach();
//        Log.i("lifeFragment", "onDetach");
//        actionBar.show();
//        actionBarFrag.hide();
//    }


}
