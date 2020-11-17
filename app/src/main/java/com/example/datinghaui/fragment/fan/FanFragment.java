package com.example.datinghaui.fragment.fan;

import android.content.Context;
import android.view.animation.AnimationUtils;
import android.view.animation.LayoutAnimationController;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datinghaui.R;
import com.example.datinghaui.base.BaseFragment;
import com.example.datinghaui.callback.BottomNavigationListerner;
import com.example.datinghaui.callback.FanCallBack;
import com.example.datinghaui.databinding.FragFanBinding;
import com.example.datinghaui.model.User;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class FanFragment extends BaseFragment<FragFanBinding,FanViewModel> {

    BottomNavigationListerner bottomNavigationListerner;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        bottomNavigationListerner = (BottomNavigationListerner) context;
    }
    @Override
    public Class<FanViewModel> getViewmodel() {
        return FanViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.frag_fan;
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
                    if(users.size()==0){
                        showSnackbar(binding.tvFan,"Ds fan trống! Update profile hấp dẫn nhá mới nhiều fan :v ", Snackbar.LENGTH_LONG);
                    }
                    viewmodel.fanAdapter.setList(users);
                    stopAnim();
                    runLayoutAnimation(binding.rvFan);
                    viewmodel.fanAdapter.setCallBack(new FanCallBack() {
                        @Override
                        public void onClickItem(User user) {
                            Toast.makeText(getActivity(), "Click " + user.getUserName(), Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onDislikeItem(User user) {
                            Toast.makeText(getActivity(), "Dislike " + user.getUserName(), Toast.LENGTH_SHORT).show();
                            viewmodel.removeRequest(user);
                        }

                        @Override
                        public void onlikeItem(User user) {
                            Toast.makeText(getActivity(), "Like " + user.getUserName(), Toast.LENGTH_SHORT).show();
                            viewmodel.AddMatch(user);
                            viewmodel.removeRequest(user);
                        }
                    });
                }
            });
    }

    private void initRecyclerView() {
        binding.rvFan.setHasFixedSize(true);
        binding.rvFan.setLayoutManager(new GridLayoutManager(getContext(),2,RecyclerView.VERTICAL,false));
        binding.rvFan.setAdapter(viewmodel.fanAdapter);
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
