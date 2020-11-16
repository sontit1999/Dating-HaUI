package com.example.datinghaui.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.datinghaui.MainActivity;
import com.example.datinghaui.R;
import com.example.datinghaui.base.BaseActivity;
import com.example.datinghaui.databinding.ActivityLoginBinding;
import com.example.datinghaui.model.User;
import com.example.datinghaui.ui.resetpass.ResetPassActivity;
import com.example.datinghaui.ui.resgister.RegisterActivity;
import com.example.datinghaui.utils.Constant;
import com.example.datinghaui.utils.UtilsDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends BaseActivity<ActivityLoginBinding,LoginViewModel> {
    @Override
    public Class<LoginViewModel> getViewmodel() {
        return LoginViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_login;
    }

    @Override
    public void setBindingViewmodel() {
        binding.setViewmodel(viewmodel);
        event();
        getData();
    }

    private void getData() {
       Intent intent = getIntent();
       if(intent!=null){
           String phone = intent.getStringExtra("phone");
           String pass = intent.getStringExtra("pass");
           binding.edtPhone.setText(phone);
           binding.edtPass.setText(pass);
       }
    }

    private void event() {
       binding.tvFogotPass.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(LoginActivity.this, ResetPassActivity.class);
               startActivity(intent);
           }
       });
       binding.btnLogin.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               startAnim();
               String phone = binding.edtPhone.getText().toString().trim();
               String pass = binding.edtPass.getText().toString().trim();
               if(phone.equals("") || pass.equals("")){
                   showToast("Không được bỏ trống!");
               }else{
                   UtilsDatabase.getInstant().getReference(Constant.noteUser).child(phone).addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                           if(snapshot.getChildrenCount() > 0){
                               User user = snapshot.getValue(User.class);
                               if(user.getPassWord().equals(pass)){
                                   stopAnim();
                                   Constant.userCurent = user;
                                   Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                   startActivity(intent);
                               }else{
                                   stopAnim();
                                   showToast("Password không đúng . Kiểm Tra lại !");
                               }
                           }else{
                               stopAnim();
                               showToast("Số điện thoại chưa được đăng kí !!! Đăng kí hoặc sử dụng tài khoản khác nhé ^^ ");
                           }
                       }

                       @Override
                       public void onCancelled(@NonNull DatabaseError error) {
                           showToast("Lỗi " + error.getMessage());
                           stopAnim();
                       }
                   });
               }


           }
       });
       binding.tvCreateAcc.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
               startActivity(intent);
           }
       });
    }
    void startAnim(){
        binding.avi.setVisibility(View.VISIBLE);
        binding.avi.smoothToShow();
        // or avi.smoothToShow();
    }

    void stopAnim(){
        binding.avi.smoothToHide();
        // or avi.smoothToHide();
    }
}
