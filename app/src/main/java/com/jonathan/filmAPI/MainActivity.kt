package com.jonathan.filmAPI

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MainActivity : AppCompatActivity() {
    companion object{
        private const val TAG = "MainActivity"
        private const val BASE_URL = "https://api.themoviedb.org/3/"
        private const val API_KEY = "3d010ab778fb4de44bf72c89f0ecac14"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val movies = mutableListOf<TmdbMovie>()
        val adapter = MoviesAdapter(this, movies)
        rvMovies.adapter = adapter
        rvMovies.layoutManager = LinearLayoutManager(this)

        val retrofit =
            Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create())
                .build()
        val tmdbService = retrofit.create(TmdbService::class.java)

        tmdbService.searchMovies().enqueue(object : Callback<TmdbSearchResult> {
            override fun onResponse(call: Call<TmdbSearchResult>, response: Response<TmdbSearchResult>) {
                Log.i(TAG, "onResponse $response")
                val body = response.body()
                if (body == null) {
                    Log.w(TAG, "Did not receive valid response body from Tmdb API... exiting")
                    return
                }
                movies.addAll(body.movies)
                adapter.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<TmdbSearchResult>, t: Throwable) {
                Log.i(TAG, "onFailure $t")
            }
        })
    }
}
