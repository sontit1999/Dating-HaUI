package com.example.datinghaui.fragment;

import androidx.lifecycle.MutableLiveData;

import com.example.datinghaui.adapter.CardAdapter;
import com.example.datinghaui.base.BaseViewmodel;
import com.example.datinghaui.model.User;

import java.util.ArrayList;

public class HomeViewModel extends BaseViewmodel {
    MutableLiveData<ArrayList<User>> arrUser = new MutableLiveData<>();
    CardAdapter cardAdapter = new CardAdapter();

    public MutableLiveData<ArrayList<User>> getArrUser() {
        // get data
        ArrayList<User> list = new ArrayList<>();
        list.add(new User("0335275330","A","A","Minh Khai - Nhổn","hihi","21"));
        list.add(new User("0335275330","V","S","Minh Khai - Nhổn","hihi","21"));
        list.add(new User("0335275330","C","d","Minh Khai - Nhổn","hihi","21"));
        list.add(new User("0335275330","D","f","Minh Khai - Nhổn","hihi","21"));
        list.add(new User("0335275330","E","g","Minh Khai - Nhổn","hihi","21"));
        list.add(new User("0335275330","F","h","Minh Khai - Nhổn","hihi","21"));
        list.add(new User("0335275330","G","j","Minh Khai - Nhổn","hihi","21"));
        list.add(new User("0335275330","H","k","Minh Khai - Nhổn","hihi","21"));
        list.add(new User("0335275330","J","ư","Minh Khai - Nhổn","hihi","21"));
        list.add(new User("0335275330","K","t","Minh Khai - Nhổn","hihi","21"));

        //choose your favorite adapter
        arrUser.postValue(list);
        return arrUser;
    }
}
