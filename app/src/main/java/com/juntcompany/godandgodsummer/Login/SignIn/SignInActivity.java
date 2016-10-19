package com.juntcompany.godandgodsummer.Login.SignIn;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.juntcompany.godandgodsummer.Data.User;
import com.juntcompany.godandgodsummer.Manager.PropertyManager;
import com.juntcompany.godandgodsummer.R;
import com.juntcompany.godandgodsummer.Util.Rest.ApiClient;
import com.juntcompany.godandgodsummer.Util.Rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignInActivity extends AppCompatActivity {

    User user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        user = new User();

        Fragment f = new SignIn1MainFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.signin_container, f);
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


    public void callSignUpInfo(User user){
        Toast.makeText(getApplicationContext(), "message" + user, Toast.LENGTH_SHORT).show();
        Log.i("activity user", user.phoneNumber +" : " + user.birthDay);
        this.user = user;
        Log.i("activity user", this.user.phoneNumber +" : " + this.user.birthDay);
    }

    public void callSignUpLast(User user){ //마지막 signin11comfirm 프래그먼트까지 다 돌고 마칠때 호출하는 메소드
        Log.i("user", this.user.phoneNumber + this.user.email + " ::::" + user.email + user.password + user.gender);
        ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
        Call<User> call =apiInterface.createUser(user.email, user.name, user.password, user.religion, user.phoneNumber, user.gender, user.birthDay);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("response", response.body().toString());
                PropertyManager propertyManager = PropertyManager.getInstance();
                propertyManager.setCurrentTargetFaith(50);
                propertyManager.setCurrentTargetPopular(50);
                propertyManager.setCurrentTargetDonate(50);
                propertyManager.setCurrentTargetFriendly(50);
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
        finish();
    }
}
