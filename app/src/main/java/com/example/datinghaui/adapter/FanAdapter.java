package com.example.datinghaui.adapter;

import com.example.datinghaui.BR;
import com.example.datinghaui.R;
import com.example.datinghaui.base.BaseAdapter;
import com.example.datinghaui.base.CBAdapter;
import com.example.datinghaui.callback.FanCallBack;
import com.example.datinghaui.databinding.ItemFanBinding;
import com.example.datinghaui.model.User;

public class FanAdapter extends BaseAdapter<User, ItemFanBinding> {
    FanCallBack callBack;

    public void setCallBack(FanCallBack callBack) {
        this.callBack = callBack;
    }

    @Override
    public int getLayoutId() {
        return R.layout.item_fan;
    }

    @Override
    public int getIdVariable() {
        return BR.user;
    }

    @Override
    public int getIdVariableOnclick() {
        return BR.callback;
    }

    @Override
    public CBAdapter getOnclick() {
        return callBack;
    }
}
