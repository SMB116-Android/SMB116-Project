package com.example.projetandroid;

import android.app.Application;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RepositoryDb {

    private final UserDao userDao;
    private final UserFilmDao userFilmDao;
    private final FilmDao filmDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private List<User> allusers;

    RepositoryDb(Application application) {
        RoomDb db = RoomDb.getDatabase(application);
        userDao = db.userDao();
        userFilmDao = db.userFilmDao();
        filmDao = db.filmDao();
    }

    public List<User> getAllWordsFromDb() {
        return allusers;
    }

    public void getAllWordsFromDb(OnQueryCompletedListener listener) {
        executorService.submit(() -> {
            allusers = userDao.getAllWords();
            listener.onQueryComplete();
        });
    }

    public void insertWord(User user, OnQueryCompletedListener listener) {
        executorService.submit(() -> {
            userDao.insert(user);
            listener.onQueryComplete();
        });
    }

    public void deleteAllWords(OnQueryCompletedListener listener) {
        executorService.submit(() -> {
            userDao.deleteAll();
            allusers.clear();
            listener.onQueryComplete();
        });
    }

    interface OnQueryCompletedListener {
        void onQueryComplete();
    }

}
