package com.example.imagetofirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.imagetofirebase.repo.PhotoRepo;

import java.io.InputStream;

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

    public void galleryButton(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);
        intent.setType("image/*");
        startActivityForResult(intent, 1); // Get image from gallery
    }

    public void cameraButton(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        PackageManager packageManager = getPackageManager();
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            startActivityForResult(intent, 2); //get image from camera
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) { //When back from camera or gallery
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == MainActivity.RESULT_OK) { //Gallery
            Uri uri = data.getData();
            try {
                InputStream inputStream = getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        if (requestCode == 2 && resultCode == MainActivity.RESULT_OK) { //Camera
            Bundle extras = data.getExtras();
            Bitmap bitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(bitmap);
        }
    }

    public void uploadButton(View view) {
        if (imageView.getDrawable() == null) {
            System.out.println("STATUS: NO PHOTO");

        } else {
            photoRepo.uploadPhoto(imageView);
            Toast.makeText(this, "Photo uploaded", Toast.LENGTH_SHORT).show();
        }
    }


}
