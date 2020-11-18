package com.example.datinghaui.fragment.chat;

import android.content.Context;
import android.os.Bundle;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datinghaui.R;
import com.example.datinghaui.base.BaseFragment;
import com.example.datinghaui.callback.BottomNavigationListerner;
import com.example.datinghaui.callback.ChatCallback;
import com.example.datinghaui.callback.SwipeToDeleteCallback;
import com.example.datinghaui.databinding.FragChatBinding;
import com.example.datinghaui.model.User;

import java.util.ArrayList;

public class ChatFragment extends BaseFragment<FragChatBinding,ChatViewModel> {
    BottomNavigationListerner bottomNavigationListerner;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        bottomNavigationListerner = (BottomNavigationListerner) context;
    }
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
                 stopAnim();
                 runLayoutAnimation(binding.rvChat);
                 viewmodel.chatAdapter.setCallback(new ChatCallback() {
                     @Override
                     public void onClickItem(User user) {
                         Bundle bundle = new Bundle();
                         bundle.putSerializable("user",user);
                         getControler().navigate(R.id.action_chatFragment_to_detealChatFragment,bundle);
                     }
                 });
             }
         });
    }

    private void initRecyclerView() {
        binding.rvChat.setHasFixedSize(true);
        binding.rvChat.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL,false));
        binding.rvChat.setAdapter(viewmodel.chatAdapter);
        SwipeToDeleteCallback callback = new SwipeToDeleteCallback(viewmodel.chatAdapter,getContext()){
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                viewmodel.removeMatched(viewmodel.chatAdapter.getList().get(viewHolder.getAdapterPosition()));
                super.onSwiped(viewHolder, direction);

            }
        };
        new ItemTouchHelper(callback).attachToRecyclerView(binding.rvChat);
    }
    private void runLayoutAnimation(RecyclerView recyclerView) {
        Context context = recyclerView.getContext();
        LayoutAnimationController controller = AnimationUtils.loadLayoutAnimation(context, R.anim.layout_animation_item_chat);
        recyclerView.setLayoutAnimation(controller);
        recyclerView.getAdapter().notifyDataSetChanged();
        recyclerView.scheduleLayoutAnimation();
    }
    void startAnim(){
        binding.avi.smoothToShow();
        // or avi.smoothToShow();
    }

    void stopAnim(){
        binding.avi.smoothToHide();
        // or avi.smoothToHide();
    }
    @Override
    public void onResume() {
        super.onResume();
        bottomNavigationListerner.onShowOrHiddenBottomNavigation(false);
    }
}
