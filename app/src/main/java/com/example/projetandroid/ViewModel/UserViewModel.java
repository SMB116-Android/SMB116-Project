package com.example.projetandroid.ViewModel;

import android.app.Application;

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

import java.util.Date;
import java.util.List;
import java.util.concurrent.Executor;

public class UserViewModel extends ViewModel {

    // REPOSITORIES

    private final UserRepository userDataSource;

    private final FilmRepository filmDataSource;

    private final Executor executor;

    // DATA

    @Nullable
    private LiveData<User> currentUser;

    public UserViewModel(UserRepository userDataSource, FilmRepository filmDataSource, Executor executor) {

        this.userDataSource = userDataSource;

        this.filmDataSource = filmDataSource;

        this.executor = executor;

    }

    public void init(int userId) {

        if (this.currentUser != null) {

            return;

        }

        //currentUser = userDataSource.getUser(userId);

    }

    // -------------

    // FOR USER

    // -------------

    /*
    public LiveData<User> getUser() { return this.currentUser;  }

    public void getUserByEmail(String email) {

        executor.execute(() -> {

            currentUser = userDataSource.getUser(email);

        });

    }

     */

    private MutableLiveData<User> currentUserM;

    public MutableLiveData<User> getCurrentUser() {
        if (currentUserM == null) {
            currentUserM = new MutableLiveData<User>();
        }
        return currentUserM;
    }

    public User getUserByEmail(String email) {

        User user = userDataSource.getUser(email);

        return user;
    }

    public boolean login(String email, String password) {

        Boolean isGood;
        isGood = userDataSource.checkEmailPassword(email, password);

        return isGood;
    }


    public void insertUser(User user) {

        executor.execute(() -> {

            userDataSource.insertUser(user);

        });

    }

    // -------------

    // FOR ITEM

    // -------------
    /*
    public LiveData<List<Item>> getItems(long userId) {

        return filmDataSource.getItems(userId);

    }

    public void createItem(String text, int category, long userId) {

        executor.execute(() -> {

            filmDataSource.createItem(new Item(text, category, userId));

        });

    }

    public void deleteItem(long itemId) {

        executor.execute(() -> filmDataSource.deleteItem(itemId));

    }

    public void updateItem(Film film) {

        executor.execute(() -> filmDataSource.updateItem(item));
    }

     */
}
