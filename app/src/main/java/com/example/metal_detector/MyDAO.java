package com.example.metal_detector;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;

@Dao
public interface MyDAO
{
    @Insert
    public void addWave(Mag_Waves waves);

    @Delete
    public void delete(Mag_Waves waves);
}