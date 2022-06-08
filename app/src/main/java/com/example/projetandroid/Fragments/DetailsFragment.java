package com.example.projetandroid.Fragments;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.projetandroid.DB.Film.Film;
import com.example.projetandroid.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DetailsFragment extends Fragment {
    private static String JSON_URL_Debut = "https://api.themoviedb.org/3/movie/";
    private static String JSON_URL = "";
    private static String JSON_URL_Suite = "?api_key=fc1e00e38c74a357b4c70550778985e7";
    private String movie_id;

    Film mFilm;
    TextView details_name;
    TextView details_overview;
    ImageView datils_img;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        Bundle extras = getActivity().getIntent().getExtras();

        if(extras.getString("movie_id") != null){
            movie_id = extras.getString("movie_id");
        }else{
            Log.i("DetailsFragment", "id non trouvée");
            movie_id = "338953";
        }

        JSON_URL = JSON_URL_Debut+movie_id+JSON_URL_Suite;

        mFilm = new Film();
        details_name = view.findViewById(R.id.details_name_txt);
        details_overview = view.findViewById(R.id.details_overview_txt);
        datils_img = view.findViewById(R.id.imageView2);

        GetData getData = new GetData();
        getData.execute();

        return view;
    }


    public class GetData extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... strings) {
            String current = "";

            try{
                URL url;
                HttpURLConnection urlConnection = null;

                try{
                    url = new URL(JSON_URL);
                    urlConnection = (HttpURLConnection) url.openConnection();

                    InputStream is = urlConnection.getInputStream();
                    InputStreamReader isr = new InputStreamReader(is);

                    int data = isr.read();
                    while(data != -1){
                        current += (char) data;
                        data = isr.read();
                    }
                    return current;
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(urlConnection != null){
                        urlConnection.disconnect();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return current;
        }

        @Override
        protected void onPostExecute(String s){
            try{
                JSONObject jsonObject = new JSONObject(s);
                Film film = new Film();
                film.setId(jsonObject.getString("id"));
                film.setName(jsonObject.getString("title"));
                film.setOverview(jsonObject.getString("overview"));
                film.setImg(jsonObject.getString("poster_path"));

                details_name.setText(film.getName());
                details_overview.setText(film.getOverview());
//                Glide.with(getApplicationContext()).load("https://image.tmdb.org/t/p/w500"+model.getImg()).into(datils_img);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}