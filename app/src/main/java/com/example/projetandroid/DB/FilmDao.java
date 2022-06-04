package com.example.projetandroid.DB;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.projetandroid.Film;

import java.util.List;

@Dao
public interface FilmDao {

    @Insert
    void insert(Film film);

    @Query("SELECT * from film_table ORDER BY releaseDate DESC")
    List<Film> getAllFilm();
}
