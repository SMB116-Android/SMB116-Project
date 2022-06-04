package com.example.projetandroid.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.projetandroid.User;

import java.util.List;


@Dao
public interface  UserDao {

    @Insert
    void insert(User user);

    @Query("DELETE FROM user_table")
    void deleteAll();

    @Query("SELECT * from user_table ORDER BY _id ASC")
    List<User> getAllWords();


}
