package com.example.projetandroid.DB.User;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public int _id;

    @NonNull
    @ColumnInfo(name = "email")
    private String _email;

    @NonNull
    @ColumnInfo(name = "password")
    private String _password;

    @NonNull
    @ColumnInfo(name = "birthDate")
    private String _birthDate;

    @Ignore
    public User(){
    }


    public User(@NonNull String email,@NonNull String password, @NonNull String birthDate){
        this._email = email;
        this._birthDate = birthDate;
        this._password = password;
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

    @NonNull
    public String get_password() {
        return _password;
    }

    public void set_password(@NonNull String _password) {
        this._password = _password;
    }
}
