package com.example.test2_practice;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface InterfaceDAOEarthquake {
    @Insert
    void insertData(List<Earthquake> earthquakeList);

    @Query("SELECT * FROM earthquake")
    List<Earthquake> getEarthquakes();

    @Delete
    void deleteFromDb(Earthquake e);

}
