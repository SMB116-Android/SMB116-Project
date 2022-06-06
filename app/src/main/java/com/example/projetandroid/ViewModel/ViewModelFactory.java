package com.example.projetandroid.ViewModel;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.projetandroid.DB.Film.FilmRepository;
import com.example.projetandroid.DB.RoomDb;
import com.example.projetandroid.DB.User.UserRepository;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private final FilmRepository filmDataSource;

    private final UserRepository userDataSource;

    private final Executor executor;

    private static ViewModelFactory factory;

    public static ViewModelFactory getInstance(Context context) {

        if (factory == null) {

            synchronized (ViewModelFactory.class) {

                if (factory == null) {

                    factory = new ViewModelFactory(context);

                }

            }

        }

        return factory;

    }

    private ViewModelFactory(Context context) {

        RoomDb database = RoomDb.getDatabase(context);

        this.filmDataSource = new FilmRepository(database.filmDao());

        this.userDataSource = new UserRepository(database.userDao());

        this.executor = Executors.newSingleThreadExecutor();

    }

    @Override

    @NotNull

    public <T extends ViewModel>  T create(Class<T> modelClass) {

        if (modelClass.isAssignableFrom(UserViewModel.class)) {

            return (T) new UserViewModel(userDataSource, filmDataSource, executor);
        }

        throw new IllegalArgumentException("Unknown ViewModel class");
    }

}
