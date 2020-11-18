package com.example.datinghaui.adapter;

import com.example.datinghaui.BR;
import com.example.datinghaui.R;
import com.example.datinghaui.base.BaseAdapter;
import com.example.datinghaui.base.CBAdapter;
import com.example.datinghaui.callback.ChatCallback;
import com.example.datinghaui.databinding.ItemChatBinding;
import com.example.datinghaui.model.User;

public class ChatAdapter extends BaseAdapter<User, ItemChatBinding> {
    ChatCallback callback;

    public void setCallback(ChatCallback callback) {
        this.callback = callback;
    }

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
        return BR.callback;
    }

    @Override
    public CBAdapter getOnclick() {
        return callback;
    }
    public void deleteItem(int pos){
        datalist.remove(pos);
        notifyItemRemoved(pos);
    }
}
