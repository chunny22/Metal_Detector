package com.example.metal_detector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.view.View;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.view.MenuItem;
import android.view.Menu;
import android.view.MenuInflater;
import android.widget.Button;
import android.widget.TextView;
import android.os.Bundle;
import android.os.Vibrator;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private SensorManager sensormanager;
    private TextView rate, X_Rate, Y_Rate, Z_Rate;
    Vibrator v;
    MediaPlayer alert_sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rate = findViewById(R.id.rate);
        X_Rate = findViewById(R.id.X_Rate);
        Y_Rate = findViewById(R.id.Y_Rate);
        Z_Rate = findViewById(R.id.Z_Rate);
        v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        alert_sound = MediaPlayer.create(this, R.raw.alert);
        Button pause = findViewById(R.id.pause);
        Button resume = findViewById(R.id.resume);

        sensormanager = (SensorManager) getSystemService(SENSOR_SERVICE);
        //TODO implement pausing the measurement and retrieving the information
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Paused detecting", Toast.LENGTH_SHORT).show();
                onPause();
            }
        });

        resume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getBaseContext(), "Resumed detecting", Toast.LENGTH_SHORT).show();
                onResume();
            }
        });
    }

    //TODO wifi/LTE
    //TODO get set POST
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.drop_menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.set) {
            Intent set_intent = new Intent(this, Settings.class);
            startActivity(set_intent);
            return false;
        }

        if (id == R.id.data) {
            Intent set_intent = new Intent(this, EnterData.class);
            startActivity(set_intent);
            return false;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensormanager.registerListener(this, sensormanager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensormanager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        boolean flag = false;

        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float X = event.values[0];
            float Y = event.values[1];
            float Z = event.values[2];

            double total = Math.sqrt((X * X) + (Y * Y) + (Z * Z));

            rate.setText(Math.round(total) + " μT");
            X_Rate.setText(Math.round(X) + " μT");
            Y_Rate.setText(Math.round(Y) + " μT");
            Z_Rate.setText(Math.round(Z) + " μT");

            while (!flag) {
                if (total >= Alert_Dialog.value) {
                    v.vibrate(500);
                    alert_sound.start();
                }
                else {
                    v.cancel();
                    alert_sound.stop();
                    flag = true;
                }
            }
        }
    }

    /*public void play() {
        if (player == null) {
            player = MediaPlayer.create(this,R.raw.alert);
        }
        player.start();
    }

    public void stop() {
        stopPlayer();
    }

    private void stopPlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        player.stop();
    }*/

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

}