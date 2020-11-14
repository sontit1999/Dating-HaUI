package com.example.datinghaui.adapter;

import com.example.datinghaui.BR;
import com.example.datinghaui.R;
import com.example.datinghaui.base.BaseAdapter;
import com.example.datinghaui.base.CBAdapter;
import com.example.datinghaui.databinding.ItemChatBinding;
import com.example.datinghaui.model.User;

public class ChatAdapter extends BaseAdapter<User, ItemChatBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.item_chat;
    }

    @Override
    public int getIdVariable() {
        return BR.user;
    }

    @Override
    public int getIdVariableOnclick() {
        return 0;
    }

    @Override
    public CBAdapter getOnclick() {
        return null;
    }
}
