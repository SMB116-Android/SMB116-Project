package com.example.projetandroid.DB.Film;

import android.app.Application;
import android.content.Context;

import com.example.projetandroid.DB.RoomDb;
import com.example.projetandroid.DB.User_Film.UserFilmDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FilmRepository {

    private final FilmDao filmDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private List<Film> allFilms;

    public FilmRepository(FilmDao filmDao) {
        this.filmDao = filmDao;
    }

    public List<Film> getAllFilmsFromDb() {
        return allFilms;
    }

    public void getAllFilms() {
        allFilms = filmDao.getAllFilm();
    }

}
