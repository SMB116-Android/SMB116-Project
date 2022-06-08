package com.example.projetandroid.DB.Film;

import org.json.JSONObject;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "film_table")
public class Film {
    @NonNull
    @PrimaryKey
    private String id;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "image")
    private String img;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "overview")
    private String overview;
    @ColumnInfo(name = "vote_average")
    private Float voteAverage;

    public Film(){}

    public Film(String id, String name, String img, float voteAverage){
        this.id = id;
        this.name=name;
        this.img = img;
        this.voteAverage = voteAverage;
    }

    public String getShortOverview(){
        if(overview.length() >= 105){
            String textTooLong = overview;
            textTooLong = textTooLong.substring(0, Math.min(textTooLong.length(), 100));
            textTooLong += "...";
            return textTooLong;
        }
        return overview;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public Float getVoteAverage() {
        return voteAverage;
    }

    public void setVoteAverage(Float voteAverage) {
        this.voteAverage = voteAverage;
    }
}
