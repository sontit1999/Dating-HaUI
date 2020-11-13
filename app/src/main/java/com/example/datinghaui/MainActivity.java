package com.example.datinghaui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.NavigationUI;

import android.os.Bundle;

import com.example.datinghaui.base.BaseActivity;
import com.example.datinghaui.databinding.ActivityMainBinding;

public class MainActivity extends BaseActivity<ActivityMainBinding,MainViewmodel> {




    @Override
    public Class<MainViewmodel> getViewmodel() {
        return MainViewmodel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void setBindingViewmodel() {
           binding.setViewmodel(viewmodel);
            setUpNavigation();
    }


    private void setUpNavigation() {
        NavHostFragment navHostFragment =  (NavHostFragment)getSupportFragmentManager()
                .findFragmentById(R.id.nav_host);
        NavigationUI.setupWithNavController(binding.bottomNav,
                navHostFragment.getNavController());
    }

}