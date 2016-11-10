package com.juntcompany.godandgodsummer.Login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.juntcompany.godandgodsummer.Data.User;
import com.juntcompany.godandgodsummer.DataStructure.UserInfoResponse;
import com.juntcompany.godandgodsummer.Login.Help.HelpActivity;
import com.juntcompany.godandgodsummer.Login.SignIn.SignInActivity;
import com.juntcompany.godandgodsummer.Main.MainActivity;
import com.juntcompany.godandgodsummer.Manager.PropertyManager;
import com.juntcompany.godandgodsummer.R;
import com.juntcompany.godandgodsummer.Util.Rest.ApiClient;
import com.juntcompany.godandgodsummer.Util.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


         final EditText edit_id = (EditText)findViewById(R.id.edit_id);
         final EditText edit_password = (EditText)findViewById(R.id.edit_password);


        Button btn = (Button)findViewById(R.id.button_login);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edit_id.getText().toString();
                String password = edit_password.getText().toString();
                Log.i("login", " dddddd " + email + password);
                PropertyManager.getInstance().setUserEmail(email);
                PropertyManager.getInstance().setUserPassword(password); // 네트워크에서 받아온 값은 암호화된 패스워드이므로 입력창에서 패스워드 저장
                loginNetwork(email, password); //네트워크 메소드
            }
        });

        btn = (Button) findViewById(R.id.button_signIn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SignInActivity.class);

                startActivity(intent);
            }
        });

        btn = (Button) findViewById(R.id.button_help);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), HelpActivity.class);
                startActivity(intent);
            }
        });



    }


    private void loginNetwork(final String email, final String password){  //로그인 할때 사용, 네트워킹이 성공하면 메인으로 이동
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<User> call = apiInterface.userLogin(email, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("test", response.body().toString() + " : " + response.body().result.message + response.body().result.status);
                if(response.body().result.status.equals("fail")){
                    Toast.makeText(getApplicationContext(), response.body().result.message, Toast.LENGTH_SHORT).show();
                } else if (response.body().result.status.equals("success")) {
                    Toast.makeText(getApplicationContext(), response.body().result.message, Toast.LENGTH_SHORT).show();
                    //아이디 비번 로컬에 저장
                    loginInfoNetwork(email); //사용자 정보를 모두 저장하기 위해 씀.

                    //Log.i("test1", response.body().toString() + " : " + response.body().current_target.faith);

                    //SQLite에 current_target값 저장(로컬 디비 날라갔을 때를 대비해서..)
                    //PropertyManager propertymanager = PropertyManager.getInstance();
                    //propertymanager.setCurrentTargetFaith(response.body().current_target.faith);
                    //propertymanager.setCurrentTargetFaith(response.body().current_target.popular);
                    //propertymanager.setCurrentTargetFaith(response.body().current_target.donate);
                    //propertymanager.setCurrentTargetFaith(response.body().current_target.friendly);


                    //로그인 성공 화면 이동
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |Intent.FLAG_ACTIVITY_CLEAR_TASK); // 메인을 들어가면 메인 전에 실행했던 TASK 를 모두 삭제
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

    private void loginInfoNetwork(String email){
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<UserInfoResponse> call = apiInterface.userInfo(email);
        call.enqueue(new Callback<UserInfoResponse>() {
            @Override
            public void onResponse(Call<UserInfoResponse> call, Response<UserInfoResponse> response) {
                if(response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), response.body().user.email, Toast.LENGTH_SHORT).show();
                    PropertyManager.getInstance().setUserEmail(response.body().user.email);
                    //패스워드는 암호화된 값을 가져오므로 입력창에서 값을 저장한다.
                    PropertyManager.getInstance().setUserName(response.body().user.name);
                    PropertyManager.getInstance().setUserId(response.body().user.userId);
                }else {

                }
            }

            @Override
            public void onFailure(Call<UserInfoResponse> call, Throwable t) {

            }
        });
    }


}
