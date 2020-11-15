package com.example.datinghaui.fragment.detailchat;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.datinghaui.R;
import com.example.datinghaui.base.BaseFragment;
import com.example.datinghaui.callback.BottomNavigationListerner;
import com.example.datinghaui.databinding.FragDetailchatBinding;

public class DetealChatFragment extends BaseFragment<FragDetailchatBinding,DetailChatViewModel> {
    BottomNavigationListerner bottomNavigationListerner;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        bottomNavigationListerner = (BottomNavigationListerner) context;
    }

    @Override
    public Class<DetailChatViewModel> getViewmodel() {
        return DetailChatViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.frag_detailchat;
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
       binding.ivAvatar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               getControler().navigate(R.id.action_detealChatFragment_to_profileFragment);
           }
       });
    }

    @Override
    public void onResume() {
        super.onResume();
        bottomNavigationListerner.onShowOrHiddenBottomNavigation(true);
    }
}
