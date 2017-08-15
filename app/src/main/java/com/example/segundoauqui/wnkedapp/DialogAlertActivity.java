package com.example.segundoauqui.wnkedapp;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class DialogAlertActivity extends AppCompatActivity {

    Button btnDA;
    Button btnCA;
    Button btnLA;
    TextView tvList;
    String [] carList;
    boolean [] checkedCars;
    ArrayList<Integer> selectedCars = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog_alert);

        btnDA = (Button) findViewById(R.id.btnDA);
        btnCA = (Button) findViewById(R.id.btnCA);
        btnLA = (Button) findViewById(R.id.btnLA);
        tvList = (TextView) findViewById(R.id.tvList);
        carList = getResources().getStringArray(R.array.carModels);
        checkedCars = new boolean[carList.length];

        btnDA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder mbuilder = new AlertDialog.Builder(DialogAlertActivity.this);
                mbuilder.setTitle(R.string.DialogAlert);
                mbuilder.setMessage(R.string.DialogMessage);
                mbuilder.setCancelable(false);
                mbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog = mbuilder.create();
                alertDialog.show();

            }
        });

        btnCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder nbuilder = new AlertDialog.Builder(DialogAlertActivity.this);
                nbuilder.setIcon(android.R.drawable.sym_def_app_icon);
                nbuilder.setTitle("Custom Alert");
                nbuilder.setMessage("This is a custom Dialog Alert!");
                nbuilder.setCancelable(false);
                nbuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

               nbuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                   @Override
                   public void onClick(DialogInterface dialogInterface, int i) {
                       dialogInterface.dismiss();

                   }
               });

                nbuilder.setNeutralButton("Neutral", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();

                    }
                });


                AlertDialog alertDialog = nbuilder.create();
                alertDialog.show();
            }
        });


        btnLA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder lBuilder = new AlertDialog.Builder(DialogAlertActivity.this);
                lBuilder.setTitle("List Dialog Alert!");
                lBuilder.setMultiChoiceItems(carList, checkedCars, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int position, boolean isChecked) {
                        if(isChecked){
                            if(!selectedCars.contains(position)){
                                selectedCars.add(position);

                            }
                            else if(selectedCars.contains(position)){
                                selectedCars.remove(position);
                                selectedCars.clear();
                            }
                        }
                    }
                });

                lBuilder.setCancelable(false);
                lBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        String item = "";
                        for(int i = 0; i < selectedCars.size(); i++){
                            item = item + carList[selectedCars.get(i)];
                            if(i != selectedCars.size()-1){
                                item = item + ",";
                            }
                        }
                        tvList.setText(item);
                    }
                });

                lBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                lBuilder.setNeutralButton("Clear All", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int which) {
                        for(int i = 0; i < checkedCars.length; i++){
                            checkedCars[i] = false;
                            selectedCars.clear();
                            tvList.setText("");
                        }

                    }
                });
                AlertDialog lDialog = lBuilder.create();
                lDialog.show();
            }
        });
    }
}
