package com.example.datinghaui.ui.resgister;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.example.datinghaui.base.BaseViewmodel;
import com.example.datinghaui.model.User;
import com.example.datinghaui.utils.Constant;
import com.example.datinghaui.utils.UtilsDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterViewModel extends BaseViewmodel {

    MutableLiveData<Boolean> statusReg = new MutableLiveData<>();
    public MutableLiveData<Boolean> getStatusReg() {
        return statusReg;
    }
    public void registerAcc(User user){
        // Write a message to the database
       UtilsDatabase.getInstant().getReference(Constant.noteUser).child(user.getPhoneNumber()).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                statusReg.postValue(true);
            }
        });
    }
}
