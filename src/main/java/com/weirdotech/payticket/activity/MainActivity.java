package com.weirdotech.payticket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.weirdotech.payticket.R;
import com.weirdotech.payticket.manager.UserMrg;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);

        //进行登录界面的还是主界面的跳转
        UserMrg userMrg = UserMrg.getInstance();
        Intent intent ;
        if(userMrg.isPrevLogin()) {
            intent = new Intent(this, HomeActivity.class);
        } else {
            intent = new Intent(this, LoginActivity.class);
        }
        startActivity(intent);
        finish();
    }
}
