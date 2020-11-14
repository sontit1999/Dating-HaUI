package com.example.datinghaui.fragment.chat;

import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;

import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datinghaui.R;
import com.example.datinghaui.base.BaseFragment;
import com.example.datinghaui.databinding.FragChatBinding;
import com.example.datinghaui.model.User;

import java.util.ArrayList;

public class ChatFragment extends BaseFragment<FragChatBinding,ChatViewModel> {
    @Override
    public Class<ChatViewModel> getViewmodel() {
        return ChatViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.frag_chat;
    }

    @Override
    public void setBindingViewmodel() {
          binding.setViewmodel(viewmodel);
    }

    @Override
    public void ViewCreated() {
         initRecyclerView();
         viewmodel.getArrUser().observe(this, new Observer<ArrayList<User>>() {
             @Override
             public void onChanged(ArrayList<User> users) {
                 viewmodel.chatAdapter.setList(users);
                 runLayoutAnimation(binding.rvChat);
             }
         });
    }

    private void initRecyclerView() {
        binding.rvChat.setHasFixedSize(true);
        binding.rvChat.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        binding.rvChat.setAdapter(viewmodel.chatAdapter);
    }
    private void runLayoutAnimation(RecyclerView recyclerView) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_item_chat);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
}
