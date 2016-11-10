package com.juntcompany.godandgodsummer.Main.Toolbar.FriendManage.ListTab;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.juntcompany.godandgodsummer.Data.Friend;
import com.juntcompany.godandgodsummer.Dialog.BeNotFriendDialogFragment;
import com.juntcompany.godandgodsummer.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FriendListFragment extends Fragment {


    public FriendListFragment() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    FriendListAdapter mAdapter;
    private static final String DIALOG = "dialog";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_friend_list, container, false);

        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        mAdapter = new FriendListAdapter();
        mAdapter.setOnItemClickListener(new FriendListAdapter.OnAdapterItemClickListener() {
            @Override
            public void onAdapterItemDeleteClick(View view, final int position) {
                Toast.makeText(getContext(), "친구 끊기", Toast.LENGTH_SHORT).show();

                BeNotFriendDialogFragment df = new BeNotFriendDialogFragment();
                //beNotFriendDialogFragment 에 리스너를 만들어줌
                df.setOnDialogResultListener(new BeNotFriendDialogFragment.OnDialogResultListener() {
                    @Override
                    public void onPositiveResult(String value) {
                        Toast.makeText(getContext(), value , Toast.LENGTH_SHORT).show();
                        Friend friend =mAdapter.getItem(position);
                        mAdapter.removeItem(friend);
                    }

                    @Override
                    public void onNegativeResult() {

                    }
                });
                df.show(getFragmentManager(), DIALOG);
            }
        });
        recyclerView.setAdapter(mAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        initData();

        return view;
    }

    private void initData(){
        for(int i=0; i<5; i++){
            Friend friend = new Friend();
            friend.friendName= i + "이름";
            friend.friendReligion = i + "종교";
            friend.friendCity = i + "도시";

            mAdapter.add(friend);
        }
    }
}