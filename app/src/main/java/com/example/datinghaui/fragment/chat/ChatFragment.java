package com.example.datinghaui.fragment.chat;

import com.example.datinghaui.R;
import com.example.datinghaui.base.BaseFragment;
import com.example.datinghaui.databinding.FragChatBinding;

public class ChatFragment extends BaseFragment<FragChatBinding,ChatViewModel> {
    @Override
    public Class<ChatViewModel> getViewmodel() {
        return ChatViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.frag_chat;
    }

    @Override
    public void setBindingViewmodel() {
          binding.setViewmodel(viewmodel);
    }

    @Override
    public void ViewCreated() {

    }
}
