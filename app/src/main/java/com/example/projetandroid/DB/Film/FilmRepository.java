package com.example.projetandroid.DB.Film;

import android.app.Application;

import com.example.projetandroid.DB.RoomDb;
import com.example.projetandroid.DB.User_Film.UserFilmDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class FilmRepository {

    private final FilmDao filmDao;
    private final UserFilmDao userFilmDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private List<Film> allFilms;

    FilmRepository(Application application) {
        RoomDb db = RoomDb.getDatabase(application);
        filmDao = db.filmDao();
        userFilmDao = db.userFilmDao();
    }

    public List<Film> getAllFilmsFromDb() {
        return allFilms;
    }

    public void getAllFilmsFromDb(OnQueryCompletedListener listener) {
        executorService.submit(() -> {
            allFilms = filmDao.getAllFilm();
            listener.onQueryComplete();
        });
    }


    interface OnQueryCompletedListener {
        void onQueryComplete();
    }

}
