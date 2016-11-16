package com.juntcompany.godandgodsummer.Login.Help;

import android.os.Bundle;
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

import com.juntcompany.godandgodsummer.Dialog.NoMatchEmailDialogFragment;
import com.juntcompany.godandgodsummer.R;

/**
 * Created by 이서현 on 2016-11-09.
 */

public class Help2EmailFragment extends Fragment {

    private static final String TITLE = "이메일 주소 입력";
    private EditText useEmail;
    private Button button;

    public Help2EmailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_help_input_email, container, false);

        Toolbar toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        HelpActivity activity = (HelpActivity) getActivity();
        activity.setSupportActionBar(toolbar);

        ActionBar actionBar = ((HelpActivity) getActivity()).getSupportActionBar();
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeAsUpIndicator(R.drawable.button_back);

        View viewToolbar = getActivity().getLayoutInflater().inflate(R.layout.toolbar_sign_in, null);
        TextView textTitle = (TextView) viewToolbar.findViewById(R.id.text_title);
        textTitle.setText(TITLE);

        button = (Button) viewToolbar.findViewById(R.id.button_next);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (true/*Todo:db와 인풋된 이메일 주소 비교.*/) {
                    Bundle bundle = new Bundle();
                    bundle.putString("index", "1");
                    bundle.putString("data", useEmail.getText().toString());
                    //        프래그먼트 세팅
                    int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();
                    Log.i("count", "policy count : " + count);
                    Fragment f = new Help3ConfirmFragment();
                    f.setArguments(bundle);
                    FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.help_container, f);
                    ft.addToBackStack("" + count);
                    ft.commit(); // 백스택에 해당 프래그먼트가 저장됨
                    //        프래그먼트 세팅
                } else {
                    NoMatchEmailDialogFragment dialog = new NoMatchEmailDialogFragment();
                    dialog.show(getFragmentManager(), "dialog");
                }
            }
        });


        useEmail = (EditText) view.findViewById(R.id.userEmail);
        useEmail.addTextChangedListener(textWatcherInput);

        actionBar.setCustomView(viewToolbar, new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, Gravity.CENTER));

        return view;
    }

    TextWatcher textWatcherInput = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (useEmail.getText().toString().contains("@") && useEmail.getText().toString().contains(".")) {
                button.setVisibility(View.VISIBLE);
            } else {
                button.setVisibility(View.INVISIBLE);
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        }

        @Override
        public void afterTextChanged(Editable s) {
        }
    };
}