package com.example.projetandroid.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.projetandroid.Film;
import com.example.projetandroid.User;

import java.util.List;

@Dao
public interface UserFilmDao {

    @Insert
    void insert(User user, Film film);

    @Query("DELETE FROM userFilm_table WHERE idUser = :idUser AND idFilm = :idFilm")
    void deleteFilm(int idUser,int idFilm);

    @Query("SELECT idFilm from userFilm_table ORDER BY idFilm ASC")
    List<Integer> getAllUserFilm();

}
