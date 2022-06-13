package com.example.projetandroid.DB.Film;

import android.app.Application;
import android.content.Context;

import com.example.projetandroid.DB.RoomDb;
import com.example.projetandroid.DB.User.User;
import com.example.projetandroid.DB.User_Film.UserFilm;
import com.example.projetandroid.DB.User_Film.UserFilmDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FilmRepository {

    private final FilmDao filmDao;
    private Film film;
    private List<Film> allFilms;

    public FilmRepository(FilmDao filmDao) {
        this.filmDao = filmDao;
    }

    public boolean userFilmAlreadyExists(int idFilm) {
        Film film = filmDao.userFilmExists(idFilm);
        if((film != null)){
            return true;
        }
        return false;
    }

    public void insertFilm(Film film) {
        filmDao.insert(film);
    }

    public List<Film> getAllUserFilm(int idUser) {
        return filmDao.getAllUserFilm(idUser);
    }

}
