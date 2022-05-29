package com.example.projetandroid;

public class User {

    int _id;
    String _email;
    String _birthDate;

    public User(){   }
    public User(int id, String email, String birthDate){
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

    public void setEmail(String email){
        this._email = email;
    }

    public String getBirthDate(){
        return this._birthDate;
    }

    public void setBirthDate(String birthDate){
        this._birthDate = birthDate;
    }
}
