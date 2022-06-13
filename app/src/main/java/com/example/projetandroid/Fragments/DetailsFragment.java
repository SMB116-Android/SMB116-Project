package com.example.projetandroid.Fragments;

import android.app.ActionBar;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.projetandroid.Adaptery;
import com.example.projetandroid.ConnectedActivity;
import com.example.projetandroid.DB.Film.Film;
import com.example.projetandroid.DB.User.User;
import com.example.projetandroid.MainActivity;
import com.example.projetandroid.R;
import com.example.projetandroid.ViewModel.UserViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

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
    private UserViewModel userViewModel;

    FloatingActionButton share, add, back;
    Film mFilm;
    TextView details_name, details_overview, details_date;
    ImageView datils_img;
    RatingBar details_ratingBar;
    View view;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        movie_id = getArguments().getString("ID");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_details, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        this.userViewModel = UserViewModel.getInstance();

        ConnectedActivity connectedActivity = (ConnectedActivity) getActivity();


        share = view.findViewById(R.id.floatingActionShare);
        add = view.findViewById(R.id.floatingActionAdd);
        back = view.findViewById(R.id.floatingActionBack);

        share.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                onShareClick();
            }
        });

        back.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                connectedActivity.returnToMenu();
            }
        });

        add.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!userViewModel.userFilmAlreadyExists(userViewModel.getCurrentUser().get_id(), Integer.parseInt(mFilm.getId()))){
                    userViewModel.insertUserFilm(userViewModel.getCurrentUser().get_id(), Integer.parseInt(mFilm.getId()));
                    userViewModel.inserFilm(mFilm);
                    Toast.makeText(connectedActivity, "Film added !", Toast.LENGTH_LONG).show();
                    connectedActivity.returnToMenu();
                }else{
                    Toast.makeText(connectedActivity, "Film already added !", Toast.LENGTH_LONG).show();
                }

            }
        });

        JSON_URL = JSON_URL_Debut+movie_id+JSON_URL_Suite;

        mFilm = new Film();
        details_name = view.findViewById(R.id.details_name_txt);
        details_overview = view.findViewById(R.id.details_overview_txt);
        datils_img = view.findViewById(R.id.imageView2);
        details_date = view.findViewById(R.id.details_date);
        details_ratingBar = view.findViewById(R.id.details_ratingBar);

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
                film.setDate(jsonObject.getString("release_date"));
                film.setVoteAverage((float) jsonObject.getDouble("vote_average"));

                details_name.setText(film.getName());
                details_overview.setText(film.getOverview());
                Glide.with(view.getContext()).load("https://image.tmdb.org/t/p/w500"+film.getImg()).into(datils_img);
                details_date.setText(film.getDate());
                details_ratingBar.setRating(film.getVoteAverage());

                mFilm = film;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void onShareClick() {
        String txt = "Name : " +mFilm.getName() + "\n Overview : "+ mFilm.getOverview() + "\n Date : " +  mFilm.getDate() + "\n Image : " + mFilm.getImg();
        String mimeType = "text/plain";
        new ShareCompat.IntentBuilder(view.getContext())
                .setType(mimeType)
                .setChooserTitle("Share this text with: ")
                .setText(txt)
                .startChooser();

    }
}