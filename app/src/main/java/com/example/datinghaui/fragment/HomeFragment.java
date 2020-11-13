package com.example.datinghaui.fragment;

import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.example.datinghaui.R;
import com.example.datinghaui.adapter.CardAdapter;
import com.example.datinghaui.base.BaseFragment;
import com.example.datinghaui.databinding.FragHomeBinding;
import com.example.datinghaui.model.User;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import com.yuyakaido.android.cardstackview.CardStackListener;
import com.yuyakaido.android.cardstackview.Direction;

import java.util.ArrayList;

public class HomeFragment extends BaseFragment<FragHomeBinding,HomeViewModel> {
    int pos = -5;
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
        setupRecyclerviewCard();
        viewmodel.getArrUser().observe(this, new Observer<ArrayList<User>>() {
            @Override
            public void onChanged(ArrayList<User> users) {
                viewmodel.cardAdapter.setList(users);
            }
        });
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
                                Toast.makeText(getActivity(),  " Like" + viewmodel.cardAdapter.getList().get(pos).getUserName(), Toast.LENGTH_SHORT).show();
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
}
