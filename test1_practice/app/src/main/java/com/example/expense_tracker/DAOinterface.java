package com.example.expense_tracker;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DAOinterface {

    @Insert
    void insertStudent(Student s);

    @Query("SELECT * FROM Student WHERE id = :id")
    Student getStudent(int id);

    @Query("SELECT * FROM Student")
    List<Student> getStudents();
}
