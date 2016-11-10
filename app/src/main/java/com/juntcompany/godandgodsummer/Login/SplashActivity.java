package com.juntcompany.godandgodsummer.Login;

import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.juntcompany.godandgodsummer.Data.User;
import com.juntcompany.godandgodsummer.Main.MainActivity;
import com.juntcompany.godandgodsummer.Manager.PropertyManager;
import com.juntcompany.godandgodsummer.R;
import com.juntcompany.godandgodsummer.Util.Rest.ApiClient;
import com.juntcompany.godandgodsummer.Util.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Handler mHandler = new Handler(Looper.getMainLooper());
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(PropertyManager.getInstance().getUserEmail()!=null&& !PropertyManager.getInstance().getUserEmail().equals("")) { // 로컬에 아이디가 있으면
                    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
                    String userEmail = PropertyManager.getInstance().getUserEmail();
                    String password= PropertyManager.getInstance().getUserPassword();
                    Log.i("splash", userEmail + password);
                    Call<User> call = apiInterface.userLogin(userEmail,password);
                    call.enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {
                            Log.i("splash1", "test");
                            if(response.isSuccessful()){
                                if (response.body().result.status.equals("success")) {
                                    //로그인 성공 화면 이동
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // 메인을 들어가면 메인 전에 실행했던 TASK 를 모두 삭제
                                    startActivity(intent);
                                }
                            }else {
                                Toast.makeText(getApplicationContext(), "네트워크 상태를 확인해 주세요", Toast.LENGTH_SHORT).show();

                            }
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {
                            Toast.makeText(getApplicationContext(), "서버 상태를 확인해 주세요", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplication(), LoginActivity.class);
                            //Intent intent = new Intent(getApplication(), MainActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    });

                }else { //로컬에 아이디가 없으면, 회원가입을 한번도 하지 않은 유저
                    Intent intent = new Intent(getApplication(), LoginActivity.class);
                    //Intent intent = new Intent(getApplication(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },1900);

    }
}
