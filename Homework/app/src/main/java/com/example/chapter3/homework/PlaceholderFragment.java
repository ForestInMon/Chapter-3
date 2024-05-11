package com.example.chapter3.homework;


import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ArrayAdapter;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {

    private LottieAnimationView animation_view;
    private ListView list_view;
    private AnimatorSet animatorSet;

    private String[] peopleList = {"好友1","好友2","好友3"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        View view = inflater.inflate(R.layout.fragment_placeholder, container,
                false);
        animation_view = view.findViewById(R.id.animation_view);
        list_view = view.findViewById(R.id.list_view);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                ArrayAdapter<String> adapter =new ArrayAdapter<String>(getView().getContext(),android.R.layout.simple_list_item_1,peopleList);
                list_view.setAdapter(adapter);
                ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(animation_view, "alpha", 1.0f,0.0f);
                alphaAnimation.setDuration(1000);
                ObjectAnimator alphaList = ObjectAnimator.ofFloat(list_view, "alpha", 0.0f, 1.0f);
                alphaList.setDuration(1000);
                animatorSet = new AnimatorSet();
                animatorSet.playTogether(alphaAnimation,alphaList);
                animatorSet.start();
            }
        }, 5000);
    }
}
