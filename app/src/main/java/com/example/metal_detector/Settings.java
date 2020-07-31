package com.example.metal_detector;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Settings extends AppCompatActivity implements Alert_Dialog.Alert_DialogListener {
    private TextView alert_rate;
    SwitchCompat sound, vibrate;
    static boolean sound_toggle, vibrate_toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        alert_rate = findViewById(R.id.alert_rate);
        Button alert_adjust = findViewById(R.id.alert_adjust);
        sound = findViewById(R.id.sound);
        vibrate = findViewById(R.id.vibrate);

        SharedPreferences sharedPreferences = getSharedPreferences("save", MODE_PRIVATE);
        SharedPreferences sharedPreferences1 = getSharedPreferences("vibrate", MODE_PRIVATE);
        sound.setChecked(sharedPreferences.getBoolean("value",true));
        vibrate.setChecked(sharedPreferences1.getBoolean("value",true));

        alert_adjust.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               open_adjust();
        }
        });
        //TODO fix the switches for vibrate and sound
        sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sound.isChecked()) {
                    SharedPreferences.Editor edit = getSharedPreferences("sound on", MODE_PRIVATE).edit();
                    edit.putBoolean("value", true);
                    edit.apply();
                    sound.setChecked(true);
                    Toast.makeText(getBaseContext(),"Sound is on", Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences.Editor edit = getSharedPreferences("sound off", MODE_PRIVATE).edit();
                    edit.putBoolean("value", false);
                    edit.apply();
                    sound.setChecked(false);
                    Toast.makeText(getBaseContext(),"Sound is off", Toast.LENGTH_SHORT).show();
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
                    Toast.makeText(getBaseContext(), "Vibration is now on", Toast.LENGTH_SHORT).show();
                }
                else {
                    SharedPreferences.Editor editor = getSharedPreferences("vibrate off", MODE_PRIVATE).edit();
                    editor.putBoolean("value", false);
                    editor.apply();
                    vibrate.setChecked(false);
                    Toast.makeText(getBaseContext(),"Vibration is now off", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void open_adjust() {
        Alert_Dialog alert_dialog = new Alert_Dialog();
        alert_dialog.show(getSupportFragmentManager(), "Adjustment Dialog");
    }

    @Override
    public void applyTexts(String adjusted_rate) {
        alert_rate.setText(adjusted_rate);
    }
}