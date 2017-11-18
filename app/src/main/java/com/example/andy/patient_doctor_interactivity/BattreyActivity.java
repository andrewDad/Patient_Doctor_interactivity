package com.example.andy.patient_doctor_interactivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;

public class BattreyActivity extends AppCompatActivity {
    private BroadcastReceiver bcr = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int level = intent.getIntExtra("level", 0);
            ProgressBar progressBar = (ProgressBar) findViewById(R.id.progress);
            progressBar.setProgress(level);

            TextView tv = (TextView) findViewById(R.id.level);
            tv.setText("Battery Level" + Integer.toString(level) + "%");

            if (level < 70) {
                Toast.makeText(BattreyActivity.this, "Sorry Battery is low", Toast.LENGTH_SHORT).show();
                AlertDialog.Builder alertBuilder = new AlertDialog.Builder(BattreyActivity.this);
                alertBuilder.setTitle("Low Battery");
                alertBuilder.setMessage("You have low battery, pleas econnect to the power sourse");
                alertBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }

                });
                AlertDialog alertDialog = alertBuilder.create();
                alertDialog.show();

            }
        }
    };
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_battrey);

            registerReceiver(bcr, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));

        }

    }


