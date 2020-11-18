package com.example.datinghaui.fragment.chat;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.datinghaui.adapter.CardAdapter;
import com.example.datinghaui.adapter.ChatAdapter;
import com.example.datinghaui.base.BaseViewmodel;
import com.example.datinghaui.model.User;
import com.example.datinghaui.utils.Constant;
import com.example.datinghaui.utils.UtilsDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ChatViewModel extends BaseViewmodel {
    MutableLiveData<ArrayList<User>> arrUser = new MutableLiveData<>();
    ChatAdapter chatAdapter = new ChatAdapter();

    public MutableLiveData<ArrayList<User>> getArrUser() {

        UtilsDatabase.getInstant().getReference(Constant.noteMatched).child(Constant.userCurent.getPhoneNumber()).addValueEventListener(new ValueEventListener() {
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

        return arrUser;
    }
    public void removeMatched(User user){
        UtilsDatabase.getInstant().getReference(Constant.noteMatched).child(Constant.userCurent.getPhoneNumber()).child(user.getPhoneNumber()).removeValue();
        UtilsDatabase.getInstant().getReference(Constant.noteMatched).child(user.getPhoneNumber()).child(Constant.userCurent.getPhoneNumber()).removeValue();
    }
}
