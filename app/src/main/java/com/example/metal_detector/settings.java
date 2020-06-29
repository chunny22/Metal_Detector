package com.example.metal_detector;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class settings extends AppCompatActivity implements Alert_Dialog.Alert_DialogListener {
    private TextView alertrate;
    private Button alertadjust;
    public Switch sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        alertrate = (TextView) findViewById(R.id.alert_rate);
        alertadjust = (Button) findViewById(R.id.alert_adjust);
        sound = (Switch) findViewById(R.id.sound);

        alertadjust.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               open_adjust();
        }
        });

        sound.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    Toast.makeText(getBaseContext(), "소리가 꺼졌습니다", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(getBaseContext(), "소리가 켜졌습니다", Toast.LENGTH_SHORT).show();
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