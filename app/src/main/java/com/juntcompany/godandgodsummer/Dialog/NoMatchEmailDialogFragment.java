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


public class NoMatchEmailDialogFragment extends DialogFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, R.style.MyDialog);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_no_match_email_dialog, container, false);

//        final Button btn_research = (Button)view.findViewById(R.id.button_reserch);

        setCancelable(true);

//        btn_research.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i = new Intent(view.getContext(), HelpFindPhoneActivity.class);
//                startActivity(i);
//                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
//                dismiss();
//            }
//        });

        return view;
    }
}