package com.juntcompany.godandgodsummer.Login.Help;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.juntcompany.godandgodsummer.Dialog.NoMatchPhoneDialogFragment;
import com.juntcompany.godandgodsummer.Login.SignIn.SignIn4PhoneFragment;
import com.juntcompany.godandgodsummer.Login.SignIn.SignInActivity;
import com.juntcompany.godandgodsummer.R;

/**
 * Created by 이서현 on 2016-11-09.
 */

public class Help2PhoneFragment extends Fragment {

    private EditText inputPhoneNumber;
    private Button next, receiveCode, receiveCodeToEmail;
    private static final String TITLE = "휴대폰 번호 입력";

    public Help2PhoneFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_input_email, container, false);

        inputPhoneNumber = (EditText) view.findViewById(R.id.inputPhone);
        next = (Button) view.findViewById(R.id.next);
        receiveCode = (Button) view.findViewById(R.id.receiveCode1);
        receiveCodeToEmail = (Button) view.findViewById(R.id.receiveCodeToEmail);

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

        inputPhoneNumber.addTextChangedListener(textWatcherInput);

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (true/*todo: db에서 일치하는 데이터가 있을 때.*/) {
                    int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();
                    Log.i("count", "policy count : " + count);
                    Fragment f = new Help3ConfirmFragment();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.help_container, f);
                    ft.addToBackStack("" + count);
                    ft.commit(); //백스택에 해당 프래그먼트가 저장됨
                } else { //
                    NoMatchPhoneDialogFragment dialog = new NoMatchPhoneDialogFragment();
                    dialog.show(getFragmentManager(), "dialog");
                }
            }
        });
        receiveCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendCode();
            }
        });
        receiveCodeToEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();
                Log.i("count", "policy count : " + count);
                Fragment f = new Help2EmailFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.help_container, f);
                ft.addToBackStack("" + count);
                ft.commit(); //백스택에 해당 프래그먼트가 저장됨
            }
        });


        actionBar.setCustomView(viewToolbar, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

        return view;
    }

    TextWatcher textWatcherInput = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (inputPhoneNumber.getText().toString().length() >= 12) {
                Toast.makeText(getActivity(), "올바른 핸드폰 번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
                next.setVisibility(View.INVISIBLE);
            } else if (inputPhoneNumber.getText().toString().length() == 11 ||
                    inputPhoneNumber.getText().toString().length() == 10) {
                next.setVisibility(View.VISIBLE);
            } else {
                next.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };

    private void goActivity(Class c) {
        Intent i = new Intent(getActivity(), c);
        i.putExtra("index", 2);
        i.putExtra("data", inputPhoneNumber.getText().toString());
        startActivity(i);
    }

    private void sendCode() {
        //Todo: 코드 전송부분 구현.
    }

}