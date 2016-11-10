package com.juntcompany.godandgodsummer.Dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

//import com.juntcompany.godandgodsummer.Login.Help.HelpFindPhoneActivity;
import com.juntcompany.godandgodsummer.R;


public class NoMatchPhoneDialogFragment extends DialogFragment {
    public NoMatchPhoneDialogFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_no_match_phone_number_dialog, container, false);

        //final Button btn_reserch = (Button) view.findViewById(R.id.button_reserch);

        setCancelable(true);
        getDialog().setCanceledOnTouchOutside(true);

//        btn_reserch.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(view.getContext(), HelpFindPhoneActivity.class);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK); //Todo: 확인해보기.
//                startActivity(i);
//                dismiss();
//            }
//        });

        return view;
    }
}