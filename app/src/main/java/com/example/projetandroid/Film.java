package com.example.projetandroid;

import org.json.JSONObject;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

public class Film {
    public String PosterPath;
    public Boolean Adult;
    public String Overview;
    public String ReleaseDate;
    public int[] GenreIds;
    public int Id;
    public String OriginalTitle;
    public String OriginalLanguage;
    public String Title;
    public String BackdropPath;
    public float Popularity;
    public int VoteCount;
    public Boolean Video;
    public float VoteAverage;

    public Film(JSONObject jsonObject) {
        this.PosterPath = jsonObject.optString("poster_path");
        this.Adult = jsonObject.optBoolean("adult");
        this.Overview = jsonObject.optString("overview");
        this.ReleaseDate = jsonObject.optString("release_date");
        //this.GenreIds = jsonObject.getJSONArray("genre_ids");
        this.Id = jsonObject.optInt("id");
        this.OriginalTitle = jsonObject.optString("original_title");
        this.OriginalLanguage = jsonObject.optString("original_language");
        this.Title = jsonObject.optString("title");
        this.BackdropPath = jsonObject.optString("backdrop_path");
        this.Popularity = jsonObject.optLong("popularity");
        this.VoteCount = jsonObject.optInt("vote_count");
        this.Video = jsonObject.optBoolean("video");
        this.VoteAverage = jsonObject.optLong("vote_average");
    }

    // https://api.themoviedb.org/3/movie/popular?api_key=fc1e00e38c74a357b4c70550778985e7&language=en-US&page=1

}
