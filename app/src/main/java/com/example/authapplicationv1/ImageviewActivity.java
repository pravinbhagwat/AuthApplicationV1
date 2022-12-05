package com.example.authapplicationv1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContract;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;

public class ImageviewActivity extends AppCompatActivity {

    ShapeableImageView imageView;
    MaterialButton clickImage, selectImage;
    MaterialButton exit;

    ActivityResultLauncher<String> mTakePhoto;
    ActivityResultLauncher<Intent> CTakePhoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageview);

        imageView = findViewById(R.id.imageView_imageView);
        clickImage = findViewById(R.id.button_clickImage);
        selectImage = findViewById(R.id.button_selactImage);
        exit  = findViewById(R.id.button_exit);


        // Selecting Image from Gallery
        mTakePhoto = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                imageView.setImageURI(result);
            }
        });

        // Capturing Image from Camera
        CTakePhoto = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
            @Override
            public void onActivityResult(ActivityResult result) {
                if(result.getResultCode() == Activity.RESULT_OK) {
                    Bitmap bitmap = (Bitmap) result.getData().getExtras().get("data");
                    imageView.setImageBitmap(bitmap);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Camera is not working", Toast.LENGTH_SHORT).show();
                }
            }
        });

        clickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                CTakePhoto.launch(intent);
            }
        });

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTakePhoto.launch("image/*");
            }
        });

        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}