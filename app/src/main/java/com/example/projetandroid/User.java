package com.example.projetandroid;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user_table")
public class User {

    @PrimaryKey(autoGenerate = true)
    private int _id;

    @NonNull
    @ColumnInfo(name = "email")
    private String _email;

    @NonNull
    @ColumnInfo(name = "email")
    private String _birthDate;


    public User(){   }
    public User(int id,@NonNull String email,@NonNull String birthDate){
        this._id = id;
        this._email = email;
        this._birthDate = birthDate;
    }

    public int getID(){
        return this._id;
    }

    public String getEmail(){
        return this._email;
    }

    public void setEmail(@NonNull String email){
        this._email = email;
    }

    public String getBirthDate(){
        return this._birthDate;
    }

    public void setBirthDate(@NonNull String birthDate){
        this._birthDate = birthDate;
    }
}
