package com.example.segundoauqui.wnkedapp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static org.greenrobot.eventbus.EventBus.TAG;

/**
 * Created by segundoauqui on 8/15/17.
 */

public class FragmentTwo extends Fragment {

    TextView tvTimer;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.secondfragment,container, false);
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.secondfragment, container, false);

        tvTimer = (TextView)view.findViewById(R.id.tvTimer);
        return view;
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
        messageEvent.getAction();
        messageEvent.getMessage();
        Log.d(TAG, "onMessageEvent: "+messageEvent.getAction() + " " + messageEvent.getMessage());

        if (messageEvent.getAction().equals("Its Updated")) {
            tvTimer.setText(messageEvent.getMessage());
        }
        Log.d(TAG, "onMessageEvent: "+messageEvent.getAction() + " " + messageEvent.getMessage());
    }





}
