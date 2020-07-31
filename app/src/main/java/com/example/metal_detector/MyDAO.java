package com.example.metal_detector;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface MyDAO
{
    /* @Query("DELETE FROM Mag_Waves")
    public void deleteAll(Mag_Waves waves); */

    @Insert
    public void addWave(Mag_Waves waves);

    @Update
    public void update(Mag_Waves waves);

    @Delete
    public void delete(Mag_Waves waves);
}