package com.example.android_basic_app;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnGoogle, btnAlarm, btnCall, btnCamera, btnMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnAlarm = findViewById(R.id.btnAlarm);
        btnCall = findViewById(R.id.btnCall);
        btnCamera = findViewById(R.id.btnCamera);
        btnMap = findViewById(R.id.btnMap);

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
                String phoneNumber = "3243888917";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                Object res = intent.resolveActivity(getPackageManager());
                startActivity(intent);
            }
        });

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    camLauncher.launch(new Intent(MediaStore.ACTION_IMAGE_CAPTURE));
                } catch (Error error) {
                    Log.d(error.toString(), "error camera: ");
                }
            }
        });

        btnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double latitude = 6.2757146;
                double longitude = -75.5864619;

                String label = "DEV MOBILE APPS CLASS ROOM";
                String uriBegin = "geo:" + latitude + "," + longitude;
                String query = latitude + "," + longitude + "(" + label + ")";
                String encodedQuery = Uri.encode(query);
                String uriString = uriBegin + "?q=" + encodedQuery;
                Uri uri = Uri.parse(uriString);

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.google.android.apps.maps");
                        startActivity(intent);
                } catch (Error error) {
                    Log.d(error.toString(), "error gps: ");
                }
            }
        });
    }

    ActivityResultLauncher<Intent> camLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
        @Override
        public void onActivityResult(ActivityResult result) {
            if(result.getResultCode() == RESULT_OK) {
                Bundle extras = result.getData().getExtras();
                Bitmap imgBitmap = (Bitmap) extras.get("data");
            }
        }
    });
}