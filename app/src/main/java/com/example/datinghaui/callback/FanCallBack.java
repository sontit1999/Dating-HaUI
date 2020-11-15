package com.example.datinghaui.callback;

import com.example.datinghaui.base.CBAdapter;
import com.example.datinghaui.model.User;

public interface FanCallBack extends CBAdapter {
    void onClickItem(User user);
    void onDislikeItem(User user);
    void onlikeItem(User user);
}
