package com.example.projetandroid;

import org.json.JSONObject;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "film_table")
public class Film {
    @ColumnInfo(name = "posterPath")
    public String PosterPath;
    @ColumnInfo(name = "adult")
    public Boolean Adult;
    @ColumnInfo(name = "overview")
    public String Overview;
    @ColumnInfo(name = "releaseDate")
    public String ReleaseDate;
    @ColumnInfo(name = "genreIds")
    public String GenreIds;

    @PrimaryKey
    public int Id;
    @ColumnInfo(name = "originalTitle")
    public String OriginalTitle;
    @ColumnInfo(name = "originalLanguage")
    public String OriginalLanguage;
    @ColumnInfo(name = "Title")
    public String Title;
    @ColumnInfo(name = "backdropPath")
    public String BackdropPath;
    @ColumnInfo(name = "popularity")
    public float Popularity;
    @ColumnInfo(name = "voteCount")
    public int VoteCount;
    @ColumnInfo(name = "video")
    public Boolean Video;
    @ColumnInfo(name = "voteAverage")
    public float VoteAverage;

    public Film(){}

    public Film(JSONObject jsonObject) {
        this.PosterPath = jsonObject.optString("poster_path");
        this.Adult = jsonObject.optBoolean("adult");
        this.Overview = jsonObject.optString("overview");
        this.ReleaseDate = jsonObject.optString("release_date");
        this.GenreIds = jsonObject.optString("genre_ids");
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
