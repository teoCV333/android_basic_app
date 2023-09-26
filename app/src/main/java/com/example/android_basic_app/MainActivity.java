package com.example.android_basic_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnGoogle, btnAlarm, btnCall;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnAlarm = findViewById(R.id.btnAlarm);
        btnCall = findViewById(R.id.btnCall);

        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goToGoogle = new Intent(Intent.ACTION_VIEW );
                goToGoogle.setData(Uri.parse("https://google.com"));
                startActivity(goToGoogle);
            }
        });

        btnAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM)
                        .putExtra(AlarmClock.EXTRA_MESSAGE, "it´s time to develop!!")
                        .putExtra(AlarmClock.EXTRA_HOUR, 10)
                        .putExtra(AlarmClock.EXTRA_MINUTES, 10);
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

        btnCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + 324388891));
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });

    }
    private void addAlarm() {

    }
}