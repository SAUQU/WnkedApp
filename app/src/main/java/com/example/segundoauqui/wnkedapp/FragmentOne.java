package com.example.segundoauqui.wnkedapp;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by segundoauqui on 8/15/17.
 */

public class FragmentOne extends Fragment {



    OnNameSetListener onNameSetListener;
    Button btnStart;
    Button btnLap;
    Button btnPause;
    long startTime = 0L, timeInMilliseconds = 0L, timeSwapBuff = 0L, updateTime = 0L;
    LinearLayout container;
    Handler customHandler = new Handler();
    Runnable updateTimeThread = new Runnable() {
        @Override
        public void run() {
            timeInMilliseconds = SystemClock.uptimeMillis() - startTime;
            updateTime = timeSwapBuff + timeInMilliseconds;
            int secs = (int) (updateTime / 1000);
            int min = secs / 60;
            secs %= 60;
            int milliseconds = (int) (updateTime % 1000);
            EventBus.getDefault().post(new MessageEvent("Its Updated",""+min+":"+String.format("%02d",secs)+":"
                    +String.format("%03d",milliseconds)));


            customHandler.postDelayed(this, 0);
        }
    };


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        btnStart = (Button) view.findViewById(R.id.btnStart);
        btnPause = (Button) view.findViewById(R.id.btnPause);
        btnLap = (Button) view.findViewById(R.id.btnLap);

        //container = (LinearLayout) view.findViewById(R.id.container);


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimeThread, 0);

            }
        });

        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                timeSwapBuff += timeInMilliseconds;
                customHandler.removeCallbacks(updateTimeThread);


            }
        });

//        btnLap.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                LayoutInflater inflater = (LayoutInflater) getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//                View addview = inflater.inflate(R.layout.row, null);
//                TextView txtValue = (TextView) addview.findViewById(R.id.txtContext);
//                txtValue.setText(tvTimer.getText());
//
//                container.addView(addview);
//
//            }
//        });


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.firstfragment, container, false);
    }


    public interface OnNameSetListener {
        public void setName(View view);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            onNameSetListener = (OnNameSetListener) activity;
        } catch (Exception e) {
        }


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

