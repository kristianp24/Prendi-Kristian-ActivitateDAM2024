package com.example.seminar_sapt4_1098;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface InterfaceDAO {

    @Insert
    void adaugaMasina(Masina m);

    @Query("SELECT * FROM Masina")
    List<Masina> getMasini();

    @Delete()
    void stergeMasina(Masina m);
}
