package com.example.metal_detector;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "waves")
public class Mag_Waves {
    @PrimaryKey
    @NonNull
    private String name;

    @ColumnInfo(name = "total_rate")
    private double rate;

    @ColumnInfo(name = "X_Rate")
    private float X_rate;

    @ColumnInfo(name = "Y_Rate")
    private float Y_rate;

    @ColumnInfo(name = "Z_Rate")
    private float Z_rate;

    //Classes
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public float getX_rate() {
        return X_rate;
    }

    public void setX_rate(float x_rate) {
        X_rate = x_rate;
    }

    public float getY_rate() {
        return Y_rate;
    }

    public void setY_rate(float y_rate) {
        Y_rate = y_rate;
    }

    public float getZ_rate() {
        return Z_rate;
    }

    public void setZ_rate(float z_rate) {
        Z_rate = z_rate;
    }
}