package com.example.practice2_test1;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface DAOInterface {
    @Insert
    void insertJucator(Jucator j);

    @Query("SELECT * FROM Jucator")
    List<Jucator> getJucatorii();

    @Update
    void updateJucator(Jucator j);

    @Delete()
    void deleteJucator(Jucator j);

}
