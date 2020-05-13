package com.example.imagetofirebase.repo;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.widget.ImageView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;

public class PhotoRepo {

    FirebaseStorage storage = FirebaseStorage.getInstance();
    StorageReference storageReference = storage.getReference();
    StorageReference pictureReference = storageReference.child("photo.jpg");

   public void uploadPhoto(ImageView imageView){
       Bitmap bitmap = ((BitmapDrawable)imageView.getDrawable()).getBitmap();
       ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
       bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
       byte[] data = byteArrayOutputStream.toByteArray();
       UploadTask uploadTask = pictureReference.putBytes(data);
       uploadTask.addOnFailureListener(new OnFailureListener() {
           @Override
           public void onFailure(@NonNull Exception e) {
               System.out.println("STATUS: FAILED TO UPLOAD");
           }
       }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
           @Override
           public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
               System.out.println("STATUS: UPLOAD COMPLETE");
           }
       });
   }
}
