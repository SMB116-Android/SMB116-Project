package com.jonathan.filmAPI

import retrofit2.Call
import retrofit2.http.GET

interface TmdbService {
    @GET("https://api.themoviedb.org/3/trending/movie/week?api_key=3d010ab778fb4de44bf72c89f0ecac14")
    fun searchMovies(
    ) : Call<TmdbSearchResult>
}