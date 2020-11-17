package com.example.datinghaui.fragment.detailchat;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.datinghaui.adapter.MessageAdapter;
import com.example.datinghaui.base.BaseViewmodel;
import com.example.datinghaui.model.Message;
import com.example.datinghaui.model.User;
import com.example.datinghaui.utils.Constant;
import com.example.datinghaui.utils.UtilsDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class DetailChatViewModel extends BaseViewmodel {

    MessageAdapter messageAdapter = new MessageAdapter();
    MutableLiveData<List<Message>> arrMessage = new MutableLiveData<>();

    public MutableLiveData<List<Message>> getArrMessage(User user) {
        String key = Constant.getPrimaryKeyWithUser(user);
        List<Message> messageList = new ArrayList<>();

        UtilsDatabase.getInstant().getReference(Constant.noteMessage).child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                messageList.clear();
                for(DataSnapshot sn : snapshot.getChildren()){

                    Message message = sn.getValue(Message.class);
                    messageList.add(message);

                }
                arrMessage.postValue(messageList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return arrMessage;
//        messageList.add(new Message("0335275330","0984352391","Hello chao ban nha","12:24"));
//        messageList.add(new Message("0335275331","0984352391","Hello chao ban nha","12:24"));
//        messageList.add(new Message("0335275330","0984352391","Hello chao ban nha","12:24"));
//        messageList.add(new Message("0335275331","0984352391","Hello chao ban nha","12:24"));
//        messageList.add(new Message("0335275330","0984352391","Hello chao ban nha","12:24"));
//        messageList.add(new Message("03352753301","0984352391","Hello chao ban nha","12:24"));

    }
    public void addMessage(Message message,User user){
        String key = Constant.getPrimaryKeyWithUser(user);
        UtilsDatabase.getInstant().getReference(Constant.noteMessage).child(key).child(System.currentTimeMillis() + "").setValue(message);
    }
}
