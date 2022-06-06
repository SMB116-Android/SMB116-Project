package com.example.projetandroid.DB.User;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface  UserDao {

    @Insert
    void insert(User user);

    @Update
    void update(User user);

    @Query("DELETE FROM user_table")
    void deleteAll();

    @Query("SELECT * from user_table WHERE id = :userId")
    User getUserById(long userId);

    @Query("SELECT * from user_table WHERE email = :email")
    User getUserByEmail(String email);

    @Query("SELECT * from user_table WHERE email = :email AND password = :password")
    User checkEmailPassword(String email, String password);
}
