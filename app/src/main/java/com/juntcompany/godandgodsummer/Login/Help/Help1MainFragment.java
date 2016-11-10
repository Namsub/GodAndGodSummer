package com.juntcompany.godandgodsummer.Login.Help;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.juntcompany.godandgodsummer.Login.SignIn.SignInActivity;
import com.juntcompany.godandgodsummer.R;

public class Help1MainFragment extends Fragment {

    private static final String TITLE = "로그인 도움말";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help, container, false);

        final TextView findEmail = (TextView)view.findViewById(R.id.findEmail);
        final TextView findPhoneNumber = (TextView)view.findViewById(R.id.findPhoneNumber);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        HelpActivity activity = (HelpActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        ActionBar actionBar = ((HelpActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.button_back);

        View viewToolbar = getActivity().getLayoutInflater().inflate(R.layout.toolbar_only_title, null);
        TextView textTitle = (TextView) viewToolbar.findViewById(R.id.text_toolbar_title);
        textTitle.setText(TITLE);

        findEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = getFragmentManager().getBackStackEntryCount();
                Log.i("count", "policy count : " + count);
                Fragment f = new Help2EmailFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.help_container, f);
                ft.addToBackStack("" + count);
                ft.commit();
            }
        });

        findPhoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int count = getFragmentManager().getBackStackEntryCount();
                Log.i("count", "policy count : " + count);
                Fragment f = new Help2PhoneFragment();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.help_container, f);
                ft.addToBackStack("" + count);
                ft.commit();
            }
        });


        actionBar.setCustomView(viewToolbar, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

        return view;
    }
}