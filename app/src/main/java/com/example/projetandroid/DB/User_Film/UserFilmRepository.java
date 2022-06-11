package com.example.projetandroid.DB.User_Film;

import com.example.projetandroid.DB.Film.Film;
import com.example.projetandroid.DB.User.User;
import com.example.projetandroid.DB.User.UserDao;

import java.util.List;

public class UserFilmRepository {

    private final UserFilmDao userFilmDao;

    public UserFilmRepository(UserFilmDao userFilmDao) {
       this.userFilmDao = userFilmDao;
    }

    public List<Integer> getAllUserFilm(int idUser) {
        return userFilmDao.getAllUserFilm();
    }

    public boolean userFilmAlreadyExists(int idUser, int idFilm) {
        UserFilm userFilm = userFilmDao.userFilmExists(idUser, idFilm);
        if(!(userFilm == null)){
            return true;
        }
        return false;
    }


    public void insertFilmInUserList(int idUser,int idFilm) {
        userFilmDao.insert(new UserFilm(idUser, idFilm));
    }

    public void deleteFilmInUserList(int idUser,int idFilm) {
        userFilmDao.delete(new UserFilm(idUser, idFilm));
    }

}
