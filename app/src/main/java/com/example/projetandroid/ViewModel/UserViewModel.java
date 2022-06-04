package com.example.projetandroid.ViewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.projetandroid.DB.User.UserRepository;
import com.example.projetandroid.DB.User.User;

public class UserViewModel extends AndroidViewModel {

    private User user;
    private UserRepository userRepository;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application);
        user = userRepository.getUserFromDb();
    }

    public void insert(User user){
        userRepository.insertUser(user, this);
    }



    private void insertUser(User user) {
        userRepository.insertUser(user, () -> {
            this.runOnUiThread(() -> {
                adapterList.add(word);
                wordsAdapter.notifyItemInserted(adapterList.size());
            });
        });
    }
}
