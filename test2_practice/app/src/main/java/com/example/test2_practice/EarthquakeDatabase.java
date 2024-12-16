package com.example.test2_practice;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Earthquake.class}, version = 1)
public abstract class EarthquakeDatabase extends RoomDatabase {
    public abstract InterfaceDAOEarthquake getInterface();
}
