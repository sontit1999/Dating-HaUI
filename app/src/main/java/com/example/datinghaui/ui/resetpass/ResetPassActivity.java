package com.example.datinghaui.ui.resetpass;

import android.content.Intent;
import android.view.View;

import com.example.datinghaui.R;
import com.example.datinghaui.base.BaseActivity;
import com.example.datinghaui.databinding.ActivityResetpassBinding;
import com.example.datinghaui.ui.LoginActivity;
import com.example.datinghaui.ui.resgister.RegisterActivity;

public class ResetPassActivity extends BaseActivity<ActivityResetpassBinding,ResetPassViewModel> {
    @Override
    public Class<ResetPassViewModel> getViewmodel() {
        return ResetPassViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_resetpass;
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
       binding.btnReset.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               showToast("Reset click!");
           }
       });
       binding.tvCreateAcc.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(ResetPassActivity.this, RegisterActivity.class);
               startActivity(intent);
           }
       });
    }
}
