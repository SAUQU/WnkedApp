package com.example.segundoauqui.wnkedapp;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

/**
 * Created by segundoauqui on 8/14/17.
 */

public class DialogView extends DialogFragment {

//
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog,container,false);
        ImageView imageView = (ImageView) view.findViewById(R.id.ivDialog);
        return view;
    }
}
