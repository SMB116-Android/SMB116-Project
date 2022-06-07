package com.jonathan.filmAPI

import com.google.gson.annotations.SerializedName

data class TmdbSearchResult(
    @SerializedName("page") val total: Float,
    @SerializedName("results") val movies: List<TmdbMovie>
)

data class TmdbMovie(
    val title: String,
    val release_date: String,
    val overview: String,
    @SerializedName("vote_average") val voteAverage: Float,
    @SerializedName("poster_path") val imageUrl: String
){
    fun convertTextTooLong() : String {
    if(overview.length >= 105){
        var textTooLong = overview
        textTooLong = textTooLong.substring(0, Math.min(textTooLong.length, 100));
        textTooLong += "..."
        return textTooLong
    }
    return overview
}}

