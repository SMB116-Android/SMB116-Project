package com.example.projetandroid;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    public int _id;

    @NonNull
    @ColumnInfo(name = "email")
    private String _email;

    @NonNull
    @ColumnInfo(name = "birthDate")
    private String _birthDate;


    public User(@NonNull String email,@NonNull String birthDate){
        this._email = email;
        this._birthDate = birthDate;
    }


    public int get_id() {
        return _id;
    }

    @NonNull
    public String get_email() {
        return _email;
    }

    public void set_email(@NonNull String _email) {
        this._email = _email;
    }

    @NonNull
    public String get_birthDate() {
        return _birthDate;
    }

    public void set_birthDate(@NonNull String _birthDate) {
        this._birthDate = _birthDate;
    }
}
