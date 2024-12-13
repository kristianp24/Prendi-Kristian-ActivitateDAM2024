package com.example.practice2_test1;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Jucator.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract DAOInterface getInterface();
}
