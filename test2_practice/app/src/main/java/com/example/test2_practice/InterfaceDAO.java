package com.example.test2_practice;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface InterfaceDAO {
    @Insert
    void insertShows(List<TvShow> shows);

    @Query("SELECT * FROM tvshow")
    List<TvShow> getShows();

    @Delete
    void deleteShow(TvShow show);
}
