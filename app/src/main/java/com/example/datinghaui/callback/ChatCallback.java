package com.example.datinghaui.callback;

import com.example.datinghaui.base.CBAdapter;
import com.example.datinghaui.model.User;

public interface ChatCallback extends CBAdapter {
    void onClickItem(User user);
}
