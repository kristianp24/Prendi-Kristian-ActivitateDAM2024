package com.example.test2_practice;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TvShow.class}, version = 1)
public abstract class ShowsDatabase extends RoomDatabase {
    public abstract InterfaceDAO getInterface();

}
