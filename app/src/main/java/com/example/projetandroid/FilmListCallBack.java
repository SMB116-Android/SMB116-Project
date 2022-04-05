package com.example.projetandroid;

import java.util.List;

public interface FilmListCallBack {
    void onSuccess(List<Film> filmList);
    void onFailure(String errorMsg);
}
