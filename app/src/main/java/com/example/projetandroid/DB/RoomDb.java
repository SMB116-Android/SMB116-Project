package com.example.projetandroid.DB;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.projetandroid.Film;
import com.example.projetandroid.User;
import com.example.projetandroid.UserFilm;

@Database(entities = {User.class, Film.class, UserFilm.class}, version = 1, exportSchema = false)
public abstract class RoomDb extends RoomDatabase {

    private static RoomDb INSTANCE;

    public abstract UserDao userDao();
    public abstract UserFilmDao userFilmDao();
    public abstract FilmDao filmDao();

    public static RoomDb getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RoomDb.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDb.class, "word_database")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

}
