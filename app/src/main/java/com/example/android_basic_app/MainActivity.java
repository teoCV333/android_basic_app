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

    private static final int REQUEST_IMAGE_CAPTURE = 1;

    Button btnGoogle, btnAlarm, btnCall, btnCamera;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnGoogle = findViewById(R.id.btnGoogle);
        btnAlarm = findViewById(R.id.btnAlarm);
        btnCall = findViewById(R.id.btnCall);
        btnCamera = findViewById(R.id.btnCamera);

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