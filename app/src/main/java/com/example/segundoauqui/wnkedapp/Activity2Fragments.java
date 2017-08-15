package com.example.segundoauqui.wnkedapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class Activity2Fragments extends AppCompatActivity{


    private static final String FIRST_FRAGMENT_TAG = "First Fragment";
    private static final String SECOND_FRAGMENT_TAG = "Second Fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity2_fragments);

        FragmentOne firstFragment = new FragmentOne();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_one, firstFragment, SECOND_FRAGMENT_TAG)
                .addToBackStack(FIRST_FRAGMENT_TAG)
                .commit();

        FragmentTwo secondFragment = new FragmentTwo();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_two, secondFragment, SECOND_FRAGMENT_TAG)
                .addToBackStack(SECOND_FRAGMENT_TAG)
                .commit();

    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageEvent messageEvent) {
        //Log.d(TAG, "onMessageEvent: "+messageEvent.getAction() + " " + messageEvent.getMessage());
    }

}

