package com.example.projetandroid;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class Repository {

    private final OkHttpClient client;
    private String apiKey = "fc1e00e38c74a357b4c70550778985e7";

    public Repository() {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    public void getFilmList(FilmListCallBack callback) {
        ArrayList<Film> todoList = new ArrayList<>();

        Request request = new Request.Builder()
                .url("https://api.themoviedb.org/3/trending/movie/week?api_key="+apiKey)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                callback.onFailure(e.getMessage());
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                try(ResponseBody responseBody = response.body()) {
                    if (!response.isSuccessful() || responseBody == null) {
                        callback.onFailure(response.message());
                        return;
                    }

                    // Api is returning an array
                    JSONObject obj = new JSONObject(responseBody.string());
                    JSONArray jsonArray = obj.getJSONArray("results");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        Film todo = new Film(jsonArray.getJSONObject(i));
                        todoList.add(todo);
                    }

                    callback.onSuccess(todoList);

                }  catch (JSONException e) {
                    callback.onFailure(e.getMessage());
                }
            }
        });
    }
}
