package com.example.metal_detector;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Mag_Waves.class}, version = 1)
public abstract class Wave_Database extends RoomDatabase
{
    public abstract MyDAO myDAO();
}