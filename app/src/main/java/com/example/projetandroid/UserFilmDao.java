package com.example.projetandroid;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

public interface UserFilmDao {

    @Insert
    void insert(Film film);

    @Query("DELETE FROM user_table")
    void deleteAll();

    @Query("SELECT * from user_table ORDER BY _id ASC")
    List<User> getAllWords();

}
