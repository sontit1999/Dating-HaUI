package com.example.datinghaui.ui.resgister;

import android.content.Intent;
import android.view.View;

import com.example.datinghaui.R;
import com.example.datinghaui.base.BaseActivity;
import com.example.datinghaui.databinding.ActivityRegisterBinding;
import com.example.datinghaui.ui.LoginActivity;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding,RegisterViewModel> {
    @Override
    public Class<RegisterViewModel> getViewmodel() {
        return RegisterViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_register;
    }

    @Override
    public void setBindingViewmodel() {
        binding.setViewmodel(viewmodel);
        event();
    }

    private void event() {
       binding.ivBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });
       binding.btnRegiser.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               showToast("Đăng kí thành công !");
               Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
               intent.putExtra("phone","0335275330");
               intent.putExtra("pass","sontit");
               intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
               startActivity(intent);
           }
       });
    }
}
