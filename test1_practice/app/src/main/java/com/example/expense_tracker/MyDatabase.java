package com.example.expense_tracker;

import androidx.room.Database;
import androidx.room.Entity;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {
    public abstract DAOinterface getInterface();
}
