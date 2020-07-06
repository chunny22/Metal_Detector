package com.example.metal_detector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements Alert_Dialog.Alert_DialogListener {
    private TextView alertrate;
    private Button alertadjust;
    SwitchCompat sound, vibrate;
    static boolean sound_toggle, vibrate_toggle;

    //TODO fix the switches for vibrate and sound
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        alertrate = (TextView) findViewById(R.id.alert_rate);
        alertadjust = (Button) findViewById(R.id.alert_adjust);
        sound = findViewById(R.id.sound);
        vibrate = findViewById(R.id.vibrate);

        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        SharedPreferences sharedPreferences1 = getSharedPreferences("vibrate",MODE_PRIVATE);
        sound.setChecked(sharedPreferences.getBoolean("value",true));
        vibrate.setChecked(sharedPreferences1.getBoolean("value",true));

        alertadjust.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               open_adjust();
        }
        });

        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sound.isChecked()) {
                    SharedPreferences.Editor edit = getSharedPreferences("sound on", MODE_PRIVATE).edit();
                    edit.putBoolean("value", true);
                    edit.apply();
                    sound.setChecked(true);
                    Toast.makeText(getBaseContext(),"소리가 켜졌습니다", Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences.Editor edit = getSharedPreferences("sound off", MODE_PRIVATE).edit();
                    edit.putBoolean("value", false);
                    edit.apply();
                    sound.setChecked(false);
                    Toast.makeText(getBaseContext(),"소리가 꺼졌습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });

        vibrate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (vibrate.isChecked()) {
                    SharedPreferences.Editor editor = getSharedPreferences("vibrate on",MODE_PRIVATE).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    vibrate.setChecked(true);
                    Toast.makeText(getBaseContext(), "진동이 켜졌습니다", Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences("vibrate off", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);
                    editor.apply();
                    vibrate.setChecked(false);
                    Toast.makeText(getBaseContext(),"진동이 꺼졌습니다", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void open_adjust() {
        Alert_Dialog alertdialog = new Alert_Dialog();
        alertdialog.show(getSupportFragmentManager(), "Adjustment Dialog");
    }

    @Override
    public void applyTexts(String adjusted_rate) {
        alertrate.setText(adjusted_rate);
    }
}