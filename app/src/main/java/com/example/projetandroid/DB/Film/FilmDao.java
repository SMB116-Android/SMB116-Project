package com.example.projetandroid.DB.Film;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface FilmDao {

    @Query("SELECT * from film_table")
    List<Film> getAllFilm();
}
