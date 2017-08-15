package com.example.segundoauqui.wnkedapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.telephony.SmsManager;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SMSActivity extends AppCompatActivity {

    Button button2;
    EditText editText4;
    EditText editText5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sms);
        if(ContextCompat.checkSelfPermission(SMSActivity.this,
                Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED){
            if(ActivityCompat.shouldShowRequestPermissionRationale(SMSActivity.this,
                    Manifest.permission.SEND_SMS)){
                ActivityCompat.requestPermissions(SMSActivity.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            } else {
                ActivityCompat.requestPermissions(SMSActivity.this,
                        new String[]{Manifest.permission.SEND_SMS}, 1);
            }
        } else {

        }

        button2 = (Button) findViewById(R.id.button2);
        editText4 = (EditText) findViewById(R.id.editText4);
        editText5 = (EditText) findViewById(R.id.editText5);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = editText4.getText().toString();
                String sms = editText5.getText().toString();

                try {
                    SmsManager smsMnager = SmsManager.getDefault();
                    smsMnager.sendTextMessage(number, null, sms, null, null);
                    Toast.makeText(SMSActivity.this, "Message Sent!", Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(SMSActivity.this, "Message Failed!", Toast.LENGTH_SHORT).show();
                }
                
            }
        });

    }

    public void btnTakeMeForward(View view) {
        Intent intent = new Intent(this, Activity2Fragments.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {

        switch(requestCode){
            case 1:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    if(ContextCompat.checkSelfPermission(SMSActivity.this,
                            Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(this, "Permission Granted!", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(this, "Permission Denied!", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }
}
