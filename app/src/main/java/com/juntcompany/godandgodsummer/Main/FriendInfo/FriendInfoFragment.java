package com.juntcompany.godandgodsummer.Main.FriendInfo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.juntcompany.godandgodsummer.Data.Timeline;
import com.juntcompany.godandgodsummer.Data.User;
import com.juntcompany.godandgodsummer.DataStructure.UserSearchResponse;
import com.juntcompany.godandgodsummer.DataStructure.UserTimelineResponse;
import com.juntcompany.godandgodsummer.Dialog.BeFriendDialogFragment;
import com.juntcompany.godandgodsummer.Dialog.BeNotFriendDialogFragment;
import com.juntcompany.godandgodsummer.Dialog.MessageNotFragment;
import com.juntcompany.godandgodsummer.Main.MainActivity;
import com.juntcompany.godandgodsummer.R;
import com.juntcompany.godandgodsummer.Util.Rest.ApiClient;
import com.juntcompany.godandgodsummer.Util.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendInfoFragment extends Fragment {  // 해당 유저 쓴 글과 유저 정보 보기


    public FriendInfoFragment() {
        // Required empty public constructor
        setHasOptionsMenu(true);
    }

    RecyclerView recyclerView;
    FriendInfoAdapter mAdapter;
    public static final String FRIEND_INFO_MESSAGE = "friend_message";
    private static final String DIALOG = "dialog";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friend_info, container, false);

        /////////       툴바 셋팅
        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        MainActivity activity = (MainActivity) getActivity();
        activity.setSupportActionBar(toolbar);
        ActionBar actionBar = ((MainActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.button_back);
//        actionBar.set
        View viewToolbar = getActivity().getLayoutInflater().inflate(R.layout.toolbar_main_search, null);

        actionBar.setCustomView(viewToolbar, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

        //////////////툴바 세팅

        ////////////////// 리사이클러뷰 세팅
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        mAdapter = new FriendInfoAdapter();
        mAdapter.setOnItemClickListener(new FriendInfoAdapter.OnAdapterItemClickListener() {
            @Override
            public void onAdapterItemAddClick(View view, int position) {
//                Toast.makeText(getContext(),"친구 추가", Toast.LENGTH_SHORT).show();
                BeFriendDialogFragment df = new BeFriendDialogFragment();
                df.show(getFragmentManager(), DIALOG);
            }

            @Override
            public void onAdapterMessageClick(View view, int position) {
//                Toast.makeText(getContext(),"메시지", Toast.LENGTH_SHORT).show();
                MessageNotFragment df = new MessageNotFragment();
                df.show(getFragmentManager(), DIALOG);
            }
        });
        recyclerView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //////데이터 세팅

        User user = (User)getArguments().getSerializable(FriendInfoFragment.FRIEND_INFO_MESSAGE);
        Toast.makeText(getContext(), user.email, Toast.LENGTH_SHORT).show();
        mAdapter.addHeader(user);
        getTimelineByEmailNetwork(user.email); //네트워크
//        initData();

        return view;
    }

    private void initData(){
//        Friend friend = new Friend();
//        mAdapter.addHeader(friend);
        for(int i=0; i<3; i++){
            Timeline timeline = new Timeline();
            mAdapter.add(timeline);
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:{
                int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();
                Log.i("backstack", ""+ count);
                getActivity().getSupportFragmentManager().popBackStack();
                int count2 = getActivity().getSupportFragmentManager().getBackStackEntryCount();
                Log.i("backstack", ""+ count2);
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    private void getTimelineByEmailNetwork(String email){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<UserTimelineResponse> call = apiInterface.getUserTimeline(email);
        call.enqueue(new Callback<UserTimelineResponse>() {
            @Override
            public void onResponse(Call<UserTimelineResponse> call, Response<UserTimelineResponse> response) {
                if(response.isSuccessful()){
                    mAdapter.addAll(response.body().results);
                }
            }

            @Override
            public void onFailure(Call<UserTimelineResponse> call, Throwable t) {
                Toast.makeText(getContext(), "네트워크 상태를 확인 부탁드립니다", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
