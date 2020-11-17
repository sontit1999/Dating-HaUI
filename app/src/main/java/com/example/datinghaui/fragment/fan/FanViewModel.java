package com.example.datinghaui.fragment.fan;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.datinghaui.adapter.ChatAdapter;
import com.example.datinghaui.adapter.FanAdapter;
import com.example.datinghaui.base.BaseViewmodel;
import com.example.datinghaui.model.User;
import com.example.datinghaui.utils.Constant;
import com.example.datinghaui.utils.UtilsDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FanViewModel extends BaseViewmodel {
    MutableLiveData<ArrayList<User>> arrUser = new MutableLiveData<>();
    FanAdapter fanAdapter = new FanAdapter();

    public MutableLiveData<ArrayList<User>> getArrUser() {
        // get data
        UtilsDatabase.getInstant().getReference(Constant.noteMatching).child(Constant.userCurent.getPhoneNumber()).child(Constant.noteFan).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<User> list = new ArrayList<>();
                for(DataSnapshot sn : snapshot.getChildren()){
                    User user = sn.getValue(User.class);
                    list.add(user);
                }
                arrUser.postValue(list);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

//        list.add(new User("0335275330","A","A","Minh Khai - Nhổn","hihi","21"));
//        list.add(new User("0335275330","V","S","Minh Khai - Nhổn","hihi","21"));
//        list.add(new User("0335275330","C","d","Minh Khai - Nhổn","hihi","21"));
//        list.add(new User("0335275330","D","f","Minh Khai - Nhổn","hihi","21"));
//        list.add(new User("0335275330","E","g","Minh Khai - Nhổn","hihi","21"));
//        list.add(new User("0335275330","F","h","Minh Khai - Nhổn","hihi","21"));
//        list.add(new User("0335275330","G","j","Minh Khai - Nhổn","hihi","21"));
//        list.add(new User("0335275330","H","k","Minh Khai - Nhổn","hihi","21"));
//        list.add(new User("0335275330","J","ư","Minh Khai - Nhổn","hihi","21"));
//        list.add(new User("0335275330","K","t","Minh Khai - Nhổn","hihi","21"));

        //choose your favorite adapter
        return arrUser;
    }
    public void removeRequest(User user){
        UtilsDatabase.getInstant().getReference(Constant.noteMatching).child(Constant.userCurent.getPhoneNumber()).child(Constant.noteFan).child(user.getPhoneNumber()).removeValue();
    }
    public void AddMatch(User user){
        UtilsDatabase.getInstant().getReference(Constant.noteMatched).child(Constant.userCurent.getPhoneNumber()).child(user.getPhoneNumber()).setValue(user);
    }
}
