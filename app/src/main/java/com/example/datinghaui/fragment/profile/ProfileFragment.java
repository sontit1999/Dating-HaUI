package com.example.datinghaui.fragment.profile;

import com.example.datinghaui.R;
import com.example.datinghaui.base.BaseFragment;
import com.example.datinghaui.databinding.FragProfileBinding;

public class ProfileFragment extends BaseFragment<FragProfileBinding,ProfileViewModel> {
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

    }
}
