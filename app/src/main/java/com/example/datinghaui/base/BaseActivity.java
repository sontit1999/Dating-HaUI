package com.example.datinghaui.base;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModelProviders;


public abstract class BaseActivity<B extends ViewDataBinding,VM extends BaseViewmodel> extends AppCompatActivity {
    protected B binding;
    protected VM viewmodel;
    public abstract Class<VM> getViewmodel();
    public abstract int getLayoutID();
    public abstract void setBindingViewmodel();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this,getLayoutID());
        viewmodel = ViewModelProviders.of(this).get(getViewmodel());
        setBindingViewmodel();
    }
    public void goToActivity(Class<BaseActivity> activity){
        Intent intent = new Intent(this,activity.getClass());
        startActivity(intent);
    }
    public void goToActivityAndClearTask(Activity activity){
        Intent intent = new Intent(this,activity.getClass());
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
    public void showToast(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
