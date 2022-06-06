package com.example.projetandroid.DB.User;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.projetandroid.DB.Film.FilmDao;
import com.example.projetandroid.DB.RoomDb;
import com.example.projetandroid.DB.User_Film.UserFilmDao;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UserRepository {

    private final UserDao userDao;
    private User user;

    public UserRepository(UserDao userDao) {
        this.userDao = userDao;
    }

    public User getCurrentUser() {
        return user;
    }

    public User getUser(long id) {
        user = userDao.getUserById(id);
        return user;
    }

    public User getUser(String email) {
        user = userDao.getUserByEmail(email);
        return user;
    }


    public void insertUser(User user) {
        userDao.insert(user);
    }

    public void updatePasswordUser(String password) {
        user.set_password(password);
        userDao.update(user);
    }

    public boolean checkEmailPassword(String email, String password) {
        user = userDao.checkEmailPassword(email, password);
        if(user != null){
            return true;
        }else{
            return false;
        }
    }

    interface OnQueryCompletedListener {
        void onQueryComplete();
    }

}
