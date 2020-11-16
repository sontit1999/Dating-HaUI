package com.example.datinghaui.ui.resgister;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.example.datinghaui.R;
import com.example.datinghaui.base.BaseActivity;
import com.example.datinghaui.databinding.ActivityRegisterBinding;
import com.example.datinghaui.model.User;
import com.example.datinghaui.ui.LoginActivity;
import com.example.datinghaui.utils.Constant;
import com.example.datinghaui.utils.UtilsDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class RegisterActivity extends BaseActivity<ActivityRegisterBinding,RegisterViewModel> {
    @Override
    public Class<RegisterViewModel> getViewmodel() {
        return RegisterViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.activity_register;
    }

    @Override
    public void setBindingViewmodel() {
        binding.setViewmodel(viewmodel);
        event();
        viewmodel.getStatusReg().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
               if(aBoolean){
                   Toast.makeText(RegisterActivity.this, "Đăng kí thành công!", Toast.LENGTH_SHORT).show();
                   stopAnim();
                   Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                   intent.putExtra("phone",binding.edtPhone.getText().toString());
                   intent.putExtra("pass",binding.edtPassword.getText().toString());
                   intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                   startActivity(intent);
               }else{
                   Toast.makeText(RegisterActivity.this, "Đăng kí không thành công!", Toast.LENGTH_SHORT).show();
                   stopAnim();
               }
            }
        });
    }

    private void event() {
       binding.ivBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
       });
       binding.btnRegiser.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String phone = binding.edtPhone.getText().toString();
               String pass = binding.edtPassword.getText().toString();
               String username = binding.edtUserName.getText().toString();
               String address = binding.edtAddress.getText().toString();
               String about = binding.edtAboutYou.getText().toString();
               String age = binding.edtAge.getText().toString();
               String favorite = binding.edtHobby.getText().toString();
               String sex = "";
               if(binding.rbNam.isChecked()){
                   sex = "nam";
               }else{
                   sex = "nu";
               }
               if(phone.equals("") || pass.equals("") || username.equals("") || address.equals("") || about.equals("") || age.equals("") || favorite.equals("")){
                   showToast("Không được bỏ trống trường nào!!!");
               }else{
                   startAnim();
                   User user = new User(phone,pass,username,address,about,age,favorite,sex);
                   // kiểm tra đã có ai dùng sdt này chưa ?
                   UtilsDatabase.getInstant().getReference(Constant.noteUser).child(user.getPhoneNumber()).addListenerForSingleValueEvent(new ValueEventListener() {
                       @Override
                       public void onDataChange(@NonNull DataSnapshot snapshot) {
                           if(snapshot.getChildrenCount() > 0){
                               showToast("Đã có người dùng sdt này đki rùi !!! Thử lại số khác nhé ^^ ");
                               stopAnim();
                           }else{
                               viewmodel.registerAcc(user);
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
