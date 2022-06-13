package com.example.projetandroid.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.projetandroid.DB.Film.Film;
import com.example.projetandroid.DB.Film.FilmRepository;
import com.example.projetandroid.DB.RoomDb;
import com.example.projetandroid.DB.User.UserRepository;
import com.example.projetandroid.DB.User.User;
import com.example.projetandroid.DB.User_Film.UserFilm;
import com.example.projetandroid.DB.User_Film.UserFilmRepository;

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

public class UserViewModel extends ViewModel  {

    private User currentUser;
    private List<Film> allUserFilms;
    private final UserRepository userRepository;
    private final UserFilmRepository userFilmRepository;
    private final FilmRepository filmRepository;
    private static UserViewModel INSTANCE;

    public UserViewModel(Context context) {

        RoomDb database = RoomDb.getDatabase(context);

        this.userRepository = new UserRepository(database.userDao());
        this.filmRepository = new FilmRepository(database.filmDao());
        this.userFilmRepository = new UserFilmRepository(database.userFilmDao());

    }

    //USER

    public static UserViewModel getInstance()
    {
        return INSTANCE;
    }

    public static void setINSTANCE(UserViewModel INSTANCE) {
        UserViewModel.INSTANCE = INSTANCE;
    }

    public void insertUser(User user) {
        userRepository.insertUser(user);
    }

    public void updatePassword(String passworrd) {
        userRepository.updatePasswordUser(passworrd);
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public User getUserByEmail(String email) {

        User user = userRepository.getUser(email);

        return user;
    }

    public boolean login(String email, String password) {

        Boolean isGood;
        isGood = userRepository.checkEmailPassword(email, password);

        if(isGood){
            currentUser = getUserByEmail(email);
        }

        return isGood;
    }

    public void login(String email) {

        currentUser = getUserByEmail(email);
    }

    //FILM

    public void insertUserFilm(int idUser, int idFilm) {
        userFilmRepository.insertFilmInUserList(idUser, idFilm);
    }

    public void deleteUserFilm(int idUser, int idFilm) {
        userFilmRepository.deleteFilmInUserList(idUser, idFilm);
    }

    public boolean userFilmAlreadyExists(int idUser, int idFilm) {
        return userFilmRepository.userFilmAlreadyExists(idUser, idFilm);
    }

    public void inserFilm(Film film) {
        if(!filmAlreadyExists(Integer.parseInt(film.getId()))){
            filmRepository.insertFilm(film);
        }

    }

    public void deleteFilm(Film film) {
        filmRepository.deleteFilm(film);
    }

    public boolean filmAlreadyExists(int idFilm) {
        return filmRepository.userFilmAlreadyExists(idFilm);
    }


    public List<Film> getAllUserFilms(int idUser) {
        return filmRepository.getAllUserFilm(idUser);
    }

}
