package com.example.datinghaui.fragment;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.datinghaui.adapter.CardAdapter;
import com.example.datinghaui.base.BaseViewmodel;
import com.example.datinghaui.model.User;
import com.example.datinghaui.utils.Constant;
import com.example.datinghaui.utils.UtilsDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends BaseViewmodel {
    MutableLiveData<ArrayList<User>> arrUser = new MutableLiveData<>();
    CardAdapter cardAdapter = new CardAdapter();

    public MutableLiveData<ArrayList<User>> getArrUser() {
        UtilsDatabase.getInstant().getReference(Constant.noteUser).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Log.d("sonzd",snapshot.getChildrenCount() + "");
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
        return arrUser;
    }

    public void setArrUser(List<User> list) {
        arrUser.postValue((ArrayList<User>) list);
    }
    public void addRequest(User user){
        String primaryKey = Constant.getPrimaryKeyWithUser(user);
        UtilsDatabase.getInstant().getReference(Constant.noteMatching).child(user.getPhoneNumber()).child(Constant.noteFan).child(Constant.userCurent.getPhoneNumber()).setValue(Constant.userCurent);
    }
}
