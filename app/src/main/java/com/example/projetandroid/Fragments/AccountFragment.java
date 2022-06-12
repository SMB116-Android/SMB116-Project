package com.example.projetandroid.Fragments;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Camera;
import android.hardware.camera2.CameraCaptureSession;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.projetandroid.ConnectedActivity;
import com.example.projetandroid.MainActivity;
import com.example.projetandroid.R;
import com.example.projetandroid.ViewModel.UserViewModel;

public class AccountFragment extends Fragment {
    private static final String TAG  = "AccountFragment";
    private static final int CAMERA_RESULT  = 1;
    private static final int RESULT_OK  = 1;
    private ConnectedActivity connectedActivity;
    private UserViewModel userViewModel;
    static final int REQUEST_IMAGE_CAPTURE = 1;


    View view;
    ImageView imageView;
    EditText passwordText;
    TextView email;
    Button changeButton;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        this.userViewModel = UserViewModel.getInstance();
        view = inflater.inflate(R.layout.fragment_account, container, false);
        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();
        connectedActivity = (ConnectedActivity) getActivity();
        imageView = view.findViewById(R.id.imgUser);
        email = view.findViewById(R.id.emailFragmentAccount);
        changeButton = view.findViewById(R.id.changeButton);
        passwordText = view.findViewById(R.id.changeEditText);

        changeButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(CheckField()){
                    if (passwordText.getText() != null) {
                        userViewModel.updatePassword(passwordText.getText().toString());
                        Toast.makeText(connectedActivity, "Password changed !", Toast.LENGTH_LONG).show();
                        passwordText.setText("");
                    }
                }

            }
        });

        imageView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                dispatchTakePictureIntent();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        email.setText(userViewModel.getCurrentUser().get_email());
    }

    private boolean CheckField() {

        if (passwordText.length() == 0) {
            passwordText.setError("Email is required");
            return false;
        }


        return true;
    }

    protected void dispatchTakePictureIntent() {

        Context context = getActivity();
        PackageManager packageManager = context.getPackageManager();
        if(packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA) == false){
            Toast.makeText(getActivity(), "This device does not have a camera.", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            startActivityForResult(takePictureIntent, CAMERA_RESULT);
        } catch (ActivityNotFoundException e) {
            Log.d(TAG, "onTakePictureClick: error with photo intent");
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_RESULT && resultCode == RESULT_OK) {
            if (data != null) {
                Bundle extras = data.getExtras();
                Bitmap imageBitmap = (Bitmap) extras.get("data");
                imageView.setImageBitmap(imageBitmap);
            }
        }

    }


}
