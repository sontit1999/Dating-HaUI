package com.example.datinghaui.fragment.fan;

import com.example.datinghaui.R;
import com.example.datinghaui.base.BaseFragment;
import com.example.datinghaui.databinding.FragFanBinding;

public class FanFragment extends BaseFragment<FragFanBinding,FanViewModel> {
    @Override
    public Class<FanViewModel> getViewmodel() {
        return FanViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.frag_fan;
    }

    @Override
    public void setBindingViewmodel() {
        binding.setViewmodel(viewmodel);
    }

    @Override
    public void ViewCreated() {

    }
}
