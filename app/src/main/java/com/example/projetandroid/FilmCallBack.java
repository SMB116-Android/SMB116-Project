package com.example.projetandroid;

public interface FilmCallBack {
    void onSuccess(Film film);
    void onFailure(String errorMsg);
}