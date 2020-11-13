package com.example.datinghaui.ui.splash;

import android.content.Intent;
import android.view.View;

import com.example.datinghaui.R;
import com.example.datinghaui.base.BaseActivity;
import com.example.datinghaui.databinding.ActivitySplashBinding;
import com.example.datinghaui.ui.LoginActivity;
import com.example.datinghaui.ui.resetpass.ResetPassActivity;
import com.example.datinghaui.ui.resgister.RegisterActivity;

public class SplashActivity extends BaseActivity<ActivitySplashBinding,SplashViewModel> {
    @Override
    public Class<SplashViewModel> getViewmodel() {
        return SplashViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    public void setBindingViewmodel() {
        binding.setViewmodel(viewmodel);
        event();
    }

    private void event() {
        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SplashActivity.this,LoginActivity.class);
                startActivity(intent);
            }
        });
       binding.btnSignUp.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(SplashActivity.this, RegisterActivity.class);
               startActivity(intent);
           }
       });
    }
}
