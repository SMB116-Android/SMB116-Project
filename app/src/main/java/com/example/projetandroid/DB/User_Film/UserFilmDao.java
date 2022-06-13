package com.example.projetandroid.DB.User_Film;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.projetandroid.DB.Film.Film;
import com.example.projetandroid.DB.User.User;

import java.util.List;

@Dao
public interface UserFilmDao {

    @Insert
    void insert(UserFilm userFilm);

    @Delete
    void delete(UserFilm userFilm);

    @Query("SELECT idFilm from userFilm_table ORDER BY idFilm ASC")
    List<Integer> getAllUserFilm();

    @Query("SELECT * from userFilm_table WHERE idUser = :idUser AND idFilm = :idFilm")
    UserFilm userFilmExists(int idUser, int idFilm);

    @Query("DELETE FROM userFilm_table WHERE idUser = :idUser AND idFilm = :idFilm")
    void deleteUserFilm(int idUser, int idFilm);

}
