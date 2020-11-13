package com.example.datinghaui.adapter;

import com.example.datinghaui.BR;
import com.example.datinghaui.R;
import com.example.datinghaui.base.BaseAdapter;
import com.example.datinghaui.base.CBAdapter;
import com.example.datinghaui.databinding.ItemCardBinding;
import com.example.datinghaui.model.User;

public class CardAdapter extends BaseAdapter<User, ItemCardBinding> {
    @Override
    public int getLayoutId() {
        return R.layout.item_card;
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
