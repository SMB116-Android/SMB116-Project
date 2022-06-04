package com.example.projetandroid;

import com.example.projetandroid.DB.Film.Film;

import java.util.List;

public interface FilmListCallBack {
    void onSuccess(List<Film> filmList);
    void onFailure(String errorMsg);
}
