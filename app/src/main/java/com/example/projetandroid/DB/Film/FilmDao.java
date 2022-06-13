package com.example.projetandroid.DB.Film;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.projetandroid.DB.User.User;
import com.example.projetandroid.DB.User_Film.UserFilm;

import java.util.List;

@Dao
public interface FilmDao {

    @Insert
    void insert(Film film);

    @Delete
    void delete(Film film);

    @Query("SELECT * from film_table WHERE id = :idFilm")
    Film userFilmExists(int idFilm);

    @Query("SELECT film_table.* from film_table, userFilm_table WHERE userFilm_table.idUser = :idUser AND film_table.id = userFilm_table.idFilm")
    List<Film> getAllUserFilm(int idUser);
}
