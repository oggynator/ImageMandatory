package com.example.imagewithrefactor;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;

import androidx.annotation.Nullable;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImageController {

    private MainActivity mainActivity;

    public ImageController(MainActivity mainActivity) {
        this.mainActivity = mainActivity;
    }

    public void HandleImageReturn(int requestCode, @Nullable Intent intent) {
        if (requestCode==0){ //photoroll
            Uri uri =intent.getData();
            try {
                InputStream inputStream = mainActivity.getContentResolver().openInputStream(uri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                mainActivity.imageView.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        if(requestCode==1){ //camera
            Bitmap bitmap = (Bitmap) intent.getExtras().get("data");
            mainActivity.imageView.setImageBitmap(bitmap);
        }
    }

}
