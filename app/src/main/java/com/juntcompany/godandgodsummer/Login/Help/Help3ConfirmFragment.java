package com.juntcompany.godandgodsummer.Login.Help;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.juntcompany.godandgodsummer.Data.User;
import com.juntcompany.godandgodsummer.Dialog.NoMatchCodeDialog;
import com.juntcompany.godandgodsummer.Login.SignIn.SignIn11ConfirmFragment;
import com.juntcompany.godandgodsummer.Login.SignIn.SignIn4PhoneFragment;
import com.juntcompany.godandgodsummer.Login.SignIn.SignInActivity;
import com.juntcompany.godandgodsummer.R;

/**
 * Created by 이서현 on 2016-11-09.
 */

public class Help3ConfirmFragment extends Fragment {
    public static final String CODE = "1234";
    private static final String TITLE = "계정 인증";

    private TextView sendMSG, userInputData;
    private Button sendCode;
    private String data, index;


    public Help3ConfirmFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        data = getArguments().getString("data");
        index = getArguments().getString("index");

        Log.d("abc", data + ", " + index);

        View view = inflater.inflate(R.layout.fragment_help_confirm_account, container, false);

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

        final Button revestCode = (Button) view.findViewById(R.id.receiveCode1);
        final Button next = (Button) view.findViewById(R.id.next);
        final EditText inputCode = (EditText) view.findViewById(R.id.inputCode);
        userInputData = (TextView) view.findViewById(R.id.userInputData);
        sendMSG = (TextView) view.findViewById(R.id.sendMSG);
        sendCode = (Button) view.findViewById(R.id.receiveCode2);


        if (index.equals("1")) {
            email();
        } else if (index.equals("2")) {
            phone();
        } else {
        }

        revestCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getActivity(), "코드가 다시 전송되었습니다.", Toast.LENGTH_SHORT).show();
                sendCodeToUser();
            }
        });
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (inputCode.getText().toString().length() == 0) {
                    Toast.makeText(getActivity(), "코드를 입력하세요.", Toast.LENGTH_SHORT).show();
                } else if (!CODE.equals(inputCode.getText().toString())) {
                    NoMatchCodeDialog dialog = new NoMatchCodeDialog();
                    dialog.show(getFragmentManager(), "dialog");
                } else {
                    //        프래그먼트 세팅
                    int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();
                    Log.i("count", "policy count : " + count);
                    Fragment f = new Help4CreatePWFragment();
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.help_container, f);
                    ft.addToBackStack("" + count);
                    ft.commit(); //백스택에 해당 프래그먼트가 저장됨
                    //        프래그먼트 세팅
                }
            }
        });
        return view;
    }

    private void email() {
        userInputData.setText(data);
        sendMSG.setText("다음 이메일 주소로 메시지가 전송되었습니다");

        sendCodeToUser();

        sendCode.setText("SMS로 코드 받기");
        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();
                Log.i("count", "policy count : " + count);
                Fragment f = new Help2PhoneFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.help_container, f);
                ft.addToBackStack("" + count);
                ft.commit();
            }
        });
    }

    private void phone() {
        String s = data;
        char[] arr_phoneNumbers = new char[s.length()];
        arr_phoneNumbers = s.toCharArray();

        if (s.length() == 10) {
            arr_phoneNumbers[3] = arr_phoneNumbers[4] = arr_phoneNumbers[5] = '*';
        } else {
            arr_phoneNumbers[3] = arr_phoneNumbers[4] = arr_phoneNumbers[5] = arr_phoneNumbers[6] = '*';
        }

        String str_phoneNumber = new String(arr_phoneNumbers, 0, arr_phoneNumbers.length);

        userInputData.setText(str_phoneNumber);
        sendMSG.setText("다음 핸드폰 번호로 메시지가 전송되었습니다");

        sendCodeToUser();

        sendCode.setText("이메일로 코드 받기");
        sendCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();
                Log.i("count", "policy count : " + count);
                Fragment f = new Help2EmailFragment();
                FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.help_container, f);
                ft.addToBackStack("" + count);
                ft.commit();
            }
        });
    }

    private void sendCodeToUser() {
        //Todo : 사용자에게 인증 코드 보내주는 부분.
    }
}