package com.example.projetandroid.DB.User;

import android.app.Application;

import com.example.projetandroid.DB.Film.FilmDao;
import com.example.projetandroid.DB.RoomDb;
import com.example.projetandroid.DB.User_Film.UserFilmDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {

    private final UserDao userDao;
    private final UserFilmDao userFilmDao;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private User user;

    public UserRepository(Application application) {
        RoomDb db = RoomDb.getDatabase(application);
        userDao = db.userDao();
        userFilmDao = db.userFilmDao();
    }

    public User getUserFromDb() {
        return user;
    }

    public void getUserFromDb(String email, OnQueryCompletedListener listener) {
        executorService.submit(() -> {
            user = userDao.getUserByEmail(email);
            listener.onQueryComplete();
        });
    }


    public void insertUser(User user, OnQueryCompletedListener listener) {
        executorService.submit(() -> {
            userDao.insert(user);
            listener.onQueryComplete();
        });
    }

    public void updatePasswordUser(String password, OnQueryCompletedListener listener) {
        executorService.submit(() -> {
            user.set_password(password);
            userDao.update(user);
            listener.onQueryComplete();
        });
    }

    interface OnQueryCompletedListener {
        void onQueryComplete();
    }

}
