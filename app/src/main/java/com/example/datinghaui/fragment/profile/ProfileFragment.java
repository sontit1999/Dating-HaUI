package com.example.datinghaui.fragment.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.datinghaui.MainActivity;
import com.example.datinghaui.R;
import com.example.datinghaui.base.BaseFragment;
import com.example.datinghaui.callback.BottomNavigationListerner;
import com.example.datinghaui.databinding.FragProfileBinding;
import com.example.datinghaui.model.User;
import com.example.datinghaui.ui.LoginActivity;
import com.example.datinghaui.utils.Constant;
import com.example.datinghaui.utils.UtilsDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class ProfileFragment extends BaseFragment<FragProfileBinding,ProfileViewModel> {
    BottomNavigationListerner bottomNavigationListerner;
    User user = null;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        bottomNavigationListerner = (BottomNavigationListerner) context;
    }
    @Override
    public Class<ProfileViewModel> getViewmodel() {
        return ProfileViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.frag_profile;
    }

    @Override
    public void setBindingViewmodel() {
      binding.setViewmodel(viewmodel);
      // get user from bundle
        Bundle bundle = getArguments();
        if(bundle!=null){
            user = (User) bundle.getSerializable("user");
        }
    }

    @Override
    public void ViewCreated() {
           event();
           bindUser();
    }

    private void bindUser() {
        if(user!=null){
            binding.ivChooseImage.setVisibility(View.GONE);
            UtilsDatabase.getInstant().getReference(Constant.noteUser).child(user.getPhoneNumber()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.getChildrenCount() > 0){
                        User user = snapshot.getValue(User.class);
                        binding.setUser(user);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getActivity(), "Error : " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            binding.ivChooseImage.setVisibility(View.VISIBLE);
            UtilsDatabase.getInstant().getReference(Constant.noteUser).child(Constant.userCurent.getPhoneNumber()).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.getChildrenCount() > 0){
                        User user = snapshot.getValue(User.class);
                        binding.setUser(user);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    Toast.makeText(getActivity(), "Error : " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });

        }
    }

    private void event() {
       binding.ivBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               getControler().popBackStack();
           }
       });
       binding.ivChooseImage.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

           }
       });
    }

    @Override
    public void onResume() {
        super.onResume();
        bottomNavigationListerner.onShowOrHiddenBottomNavigation(false);
    }
}
