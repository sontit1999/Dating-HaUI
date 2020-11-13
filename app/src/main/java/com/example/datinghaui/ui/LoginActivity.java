package com.example.datinghaui.ui;

import android.content.Intent;
import android.view.View;

import com.example.datinghaui.MainActivity;
import com.example.datinghaui.R;
import com.example.datinghaui.base.BaseActivity;
import com.example.datinghaui.databinding.ActivityLoginBinding;
import com.example.datinghaui.ui.resetpass.ResetPassActivity;
import com.example.datinghaui.ui.resgister.RegisterActivity;

public class LoginActivity extends BaseActivity<ActivityLoginBinding,LoginViewModel> {
    @Override
    public Class<LoginViewModel> getViewmodel() {
        return LoginViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    public void setBindingViewmodel() {
        binding.setViewmodel(viewmodel);
        event();
    }

    private void event() {
       binding.tvFogotPass.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(LoginActivity.this, ResetPassActivity.class);
               startActivity(intent);
           }
       });
       binding.btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(LoginActivity.this, MainActivity.class);
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               startActivity(intent);
           }
       });
       binding.tvCreateAcc.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
               startActivity(intent);
           }
       });
    }
}
