package com.example.seminar_sapt4_1098;

import androidx.room.Dao;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Masina.class}, version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public abstract InterfaceDAO getInterface();
}
