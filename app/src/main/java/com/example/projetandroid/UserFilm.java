package com.example.projetandroid;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "userFilm_table")
public class UserFilm {

    @PrimaryKey
    public int _idUserFilm;

    @NonNull
    @ColumnInfo(name = "idUser")
    private int _idUser;

    @NonNull
    @ColumnInfo(name = "idFilm")
    private int _idFilm;

    public UserFilm(int idUser,int idFilm){
        this._idUser = idUser;
        this._idFilm = idFilm;
    }

    public int get_idFilm() {
        return _idFilm;
    }

    public int get_idUser() {
        return _idUser;
    }

    public int get_idUserFilm() {
        return _idUserFilm;
    }

}
