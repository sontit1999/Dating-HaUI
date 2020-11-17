package com.example.datinghaui.fragment;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;

import com.example.datinghaui.R;
import com.example.datinghaui.adapter.CardAdapter;
import com.example.datinghaui.base.BaseFragment;
import com.example.datinghaui.callback.BottomNavigationListerner;
import com.example.datinghaui.databinding.FragHomeBinding;
import com.example.datinghaui.model.User;
import com.example.datinghaui.ui.LoginActivity;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.Direction;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment<FragHomeBinding,HomeViewModel> {
    int pos = -5;
    Animation anim;
    BottomNavigationListerner bottomNavigationListerner;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        bottomNavigationListerner = (BottomNavigationListerner) context;
    }
    @Override
    public Class<HomeViewModel> getViewmodel() {
        return HomeViewModel.class;
    }

    @Override
    public int getLayoutID() {
        return R.layout.frag_home;
    }

    @Override
    public void setBindingViewmodel() {
           binding.setViewmodel(viewmodel);
    }

    @Override
    public void ViewCreated() {
        loadAnimation();
        setupRecyclerviewCard();
        viewmodel.getArrUser().observe(this, new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                viewmodel.cardAdapter.setList(users);
            }
        });
        binding.ivLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
    }

    private void loadAnimation() {
        anim = AnimationUtils.loadAnimation(getContext(), R.anim.animation_tym);
    }

    public void startAnimation(){
        // start the animation
        binding.ivHeart.startAnimation(anim);
    }
    private void setupRecyclerviewCard() {
                CardStackLayoutManager layoutManager =  new CardStackLayoutManager(getContext(), new CardStackListener() {
                    @Override
                    public void onCardDragging(Direction direction, float ratio) {
                    }

                    @Override
                    public void onCardSwiped(Direction direction) {
                        if(pos>=0){
                            if(direction == Direction.Left){
                                Toast.makeText(getActivity(),  " DisLike " + viewmodel.cardAdapter.getList().get(pos).getUserName(), Toast.LENGTH_SHORT).show();
                            }
                            if(direction == Direction.Right){
                                startAnimation();
                                Toast.makeText(getActivity(),  " Like" + viewmodel.cardAdapter.getList().get(pos).getUserName(), Toast.LENGTH_SHORT).show();
                                viewmodel.addRequest(viewmodel.cardAdapter.getList().get(pos));
                            }
                        }
                    }

                    @Override
                    public void onCardRewound() {

                    }

                    @Override
                    public void onCardCanceled() {

                    }

                    @Override
                    public void onCardAppeared(View view, int position) {
                        pos = position;
                    }

                    @Override
                    public void onCardDisappeared(View view, int position) {

                    }
                });
                layoutManager.getCardStackListener().onCardSwiped(Direction.Left);
                binding.cardStackView.setLayoutManager(layoutManager);
                binding.cardStackView.setAdapter(viewmodel.cardAdapter);



    }

    @Override
    public void onResume() {
        super.onResume();
        bottomNavigationListerner.onShowOrHiddenBottomNavigation(false);
    }
}
