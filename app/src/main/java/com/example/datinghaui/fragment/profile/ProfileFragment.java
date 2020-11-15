package com.example.datinghaui.fragment.profile;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.datinghaui.R;
import com.example.datinghaui.base.BaseFragment;
import com.example.datinghaui.callback.BottomNavigationListerner;
import com.example.datinghaui.databinding.FragProfileBinding;

public class ProfileFragment extends BaseFragment<FragProfileBinding,ProfileViewModel> {
    BottomNavigationListerner bottomNavigationListerner;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        bottomNavigationListerner = (BottomNavigationListerner) context;
    }
    @Override
    public Class<ProfileViewModel> getViewmodel() {
        return ProfileViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.frag_profile;
    }

    @Override
    public void setBindingViewmodel() {
      binding.setViewmodel(viewmodel);
    }

    @Override
    public void ViewCreated() {
           event();
    }

    private void event() {
       binding.ivBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               getControler().popBackStack();
           }
       });
       binding.ivChooseImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

           }
       });
    }

    @Override
    public void onResume() {
        super.onResume();
        bottomNavigationListerner.onShowOrHiddenBottomNavigation(false);
    }
}
