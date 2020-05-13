package com.example.imagetofirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import com.example.imagetofirebase.repo.PhotoRepo;

public class MainActivity extends AppCompatActivity {


    private ImageView imageView;
    private PhotoRepo photoRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.imageView);
        photoRepo = new PhotoRepo();

    }

    public void onGalleryButtonPressed(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK); //Able to choose between different activities.
        intent.setType("image/*");
        startActivityForResult(intent, 1); // Activity 1
    }

    public void onCameraButtonPressed(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        PackageManager packageManager = getPackageManager();
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            startActivityForResult(intent, 2); //Activity 2
        }
    }



}
