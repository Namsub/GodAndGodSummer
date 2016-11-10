package com.juntcompany.godandgodsummer.Dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.juntcompany.godandgodsummer.R;

//import static com.juntcompany.godandgodsummer.Login.Help.HelpConfirmAccountActivity.data;

/**
 * Created by 이서현 on 2016-09-29.
 */
public class NoMatchCodeDialog extends DialogFragment {

    public NoMatchCodeDialog() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Intent intent = getActivity().getIntent();
        final int index = intent.getIntExtra("index", 0);

        View view = inflater.inflate(R.layout.fragment_no_match_code_dialog, container, false);

        final Button btn_retry = (Button) view.findViewById(R.id.button_retry); // 다시 시도 새로 화면 출력.
        final Button btn_revest = (Button) view.findViewById(R.id.button_revestCode); // 다시 받기

        setCancelable(true);
        getDialog().setCanceledOnTouchOutside(true);

        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(view.getContext(), getActivity().getClass());
                //i.putExtra("data", data);
                i.putExtra("index", index);
                startActivity(i);
                dismiss();
            }
        });
        btn_revest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { // 다이얼로그만 끄기
                sendCodeToUser();
                dismiss();
            }
        });

        return view;
    }

    private void sendCodeToUser() {
        //Todo : 사용자에게 인증 코드 보내주는 부분.
    }

}