package com.juntcompany.godandgodsummer.Login.Help;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.juntcompany.godandgodsummer.Login.LoginActivity;
import com.juntcompany.godandgodsummer.Login.SignIn.SignInActivity;
import com.juntcompany.godandgodsummer.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 이서현 on 2016-11-09.
 */

public class Help4CreatePWFragment extends Fragment {

    private static final String TITLE = "계정 비밀번호";
    private final String Passwrod_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=[\\S]+$).{6,20}";
    private EditText inputPassWord;
    private Button complete;

    public Help4CreatePWFragment() {
    }

    @Override
    public void onCreate( Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_create_password, container, false);

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

        complete = (Button) view.findViewById(R.id.complete);
        inputPassWord = (EditText) view.findViewById(R.id.inputPassWord);
        inputPassWord.addTextChangedListener(textWatcherInput);

        complete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Passwordvalidate(inputPassWord.getText().toString())) {
                    Toast.makeText(getActivity(), "비밀번호를 다시 입력해주세요.", Toast.LENGTH_SHORT).show();
                } else {
                    Intent i = new Intent(view.getContext(), LoginActivity.class);
                    startActivity(i);
                    //Todo: 사용자 계정의 비밀번호 업데이트 하기.
                }
            }
        });


        return view;
    }


    private boolean Passwordvalidate(final String hex) {
        Pattern pattern = Pattern.compile(Passwrod_PATTERN);
        Matcher matcher = pattern.matcher(hex);
        return matcher.matches();
    }

    TextWatcher textWatcherInput = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if(inputPassWord.getText().toString().length() > 5 && inputPassWord.getText().toString().length() < 21)
                complete.setVisibility(View.VISIBLE);
            else
                complete.setVisibility(View.INVISIBLE);
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
}