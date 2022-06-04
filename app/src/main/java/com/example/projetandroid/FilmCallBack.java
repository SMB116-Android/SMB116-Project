package com.example.projetandroid;

import com.example.projetandroid.DB.Film.Film;

public interface FilmCallBack {
    void onSuccess(Film film);
    void onFailure(String errorMsg);
}