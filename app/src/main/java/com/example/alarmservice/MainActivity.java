package com.example.alarmservice;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.View;
import android.widget.Toast;

import com.example.alarmservice.databinding.ActivityMainBinding;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityMainBinding binding;
    Intent intent;
    PendingIntent pendingIntent;
    AlarmManager alarmManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.button2.setOnClickListener(this);
        binding.button1.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.button2:
                startAlert();
                break;
            case R.id.button1:
                CancelAlert();
                Toast.makeText(MainActivity.this, "Alarm Stopped", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    public void startAlert() {

       intent = new Intent(this, AlarmReicever.class);
       pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 234, intent, 0);
      alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);

        alarmManager.setRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                0,
                2*60*1000,pendingIntent);

    }
    public void CancelAlert() {
       alarmManager.cancel(pendingIntent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        startAlert();
    }
}
