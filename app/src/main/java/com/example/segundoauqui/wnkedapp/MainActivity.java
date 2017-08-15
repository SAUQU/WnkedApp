package com.example.segundoauqui.wnkedapp;

import android.app.FragmentManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    PDFView pdfView;
    DialogView dialogView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pdfView = (PDFView) findViewById(R.id.pdfViewer);
        pdfView.fromAsset("review-2.pdf").load();

        final FragmentManager fragmentManager = getFragmentManager();
        final DialogView myDialogFragment = new DialogView();
        myDialogFragment.show(fragmentManager, "tagDialog");

        final Timer t = new Timer();
        t.schedule(new TimerTask() {
            public void run() {
                myDialogFragment.dismiss(); // when the task active then close the dialog
                t.cancel(); // also just top the timer thread, otherwise, you may receive a crash report
            }
        }, 3000); // after 2 second (or 2000 miliseconds), the task will be active.

    }

    public void btnTakeMeForward(View view) {
        Intent intent = new Intent(this, DialogAlertActivity.class);
        startActivity(intent);
    }


    public void btnNotification(View view) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.splashscreen);
        builder.setContentTitle("Notification");
        builder.setContentText("Hey! You want some cookies? Click me :)!");
        Intent intent = new Intent(this, SMSActivity.class);
        TaskStackBuilder stacktBuilder = TaskStackBuilder.create(this);
        stacktBuilder.addParentStack(DialogAlertActivity.class);
        stacktBuilder.addNextIntent(intent);
        PendingIntent pendingIntent = stacktBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pendingIntent);
        NotificationManager NM = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NM.notify(0, builder.build());
    }
}