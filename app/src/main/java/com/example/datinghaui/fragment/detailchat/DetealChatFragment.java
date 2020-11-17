package com.example.datinghaui.fragment.detailchat;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.datinghaui.R;
import com.example.datinghaui.base.BaseFragment;
import com.example.datinghaui.callback.BottomNavigationListerner;
import com.example.datinghaui.databinding.FragDetailchatBinding;
import com.example.datinghaui.model.Message;
import com.example.datinghaui.model.User;
import com.example.datinghaui.utils.Constant;

import java.util.List;

public class DetealChatFragment extends BaseFragment<FragDetailchatBinding,DetailChatViewModel> {
    BottomNavigationListerner bottomNavigationListerner;
    User user = null;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        bottomNavigationListerner = (BottomNavigationListerner) context;
    }

    @Override
    public Class<DetailChatViewModel> getViewmodel() {
        return DetailChatViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.frag_detailchat;
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
            bindUser();
            event();
            setupRecyclerviewMess();
            viewmodel.getArrMessage(user).observe(this, new Observer<List<Message>>() {
                @Override
                public void onChanged(List<Message> messages) {
                    viewmodel.messageAdapter.setList(messages.subList(messages.size()-10, messages.size()));
                    if(messages.size()>0){
                        binding.rvMess.smoothScrollToPosition(viewmodel.messageAdapter.getList().size()-1);
                    }
                }
            });
    }

    private void bindUser() {
        if(user!=null){
            binding.tvUserName.setText(user.getUserName());
            Glide.with(getContext()).load(user.getLinkavatar()).into(binding.ivAvatar);
        }
    }

    private void setupRecyclerviewMess() {
        binding.rvMess.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        viewmodel.messageAdapter.setUserTalk(user);
        binding.rvMess.setAdapter(viewmodel.messageAdapter);
    }

    private void event() {
       binding.ivBack.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               getControler().popBackStack();
           }
       });
       binding.ivAvatar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Bundle bundle = new Bundle();
               bundle.putSerializable("user",user);
               getControler().navigate(R.id.action_detealChatFragment_to_profileFragment,bundle);
           }
       });
       binding.ivSend.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String content = binding.edtMessage.getText().toString();
               if(content.trim().equals("")){
                   Toast.makeText(getActivity(), "Tin nhắn ko được bỏ trống nha !", Toast.LENGTH_SHORT).show();
               }else{
                   Message message = new Message(Constant.userCurent.getPhoneNumber(),user.getPhoneNumber(),content,System.currentTimeMillis() + "");
                   viewmodel.addMessage(message,user);
                   binding.edtMessage.setText("");
               }

           }
       });
    }

    @Override
    public void onResume() {
        super.onResume();
        bottomNavigationListerner.onShowOrHiddenBottomNavigation(true);
    }
}
