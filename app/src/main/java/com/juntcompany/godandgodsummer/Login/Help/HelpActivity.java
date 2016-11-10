package com.juntcompany.godandgodsummer.Login.Help;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;

import com.juntcompany.godandgodsummer.R;

/**
 * Created by 이서현 on 2016-11-09.
 */

public class HelpActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("test","1");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        Fragment f = new Help1MainFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.help_container, f);
        ft.commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home: {
                getSupportFragmentManager().popBackStack();
//                백스택에 저장한거를 하나씩 빼내는 역할로 뒤로 가기 버튼
//                대신 프래그먼트 만들때마다 백스택에 프래그먼트를 저장해야 함.
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }
}