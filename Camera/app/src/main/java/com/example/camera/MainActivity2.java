package com.example.camera;

import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        imageView = (ImageView)findViewById(R.id.imageView);
    }

    public void OpenCamera(View view){
          Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
          startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
    }

    @Override
    protected void onActivityResult(int requestCode , int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if (requestCode == REQUEST_IMAGE_CAPTURE){
             Bitmap bitmap = (Bitmap)data.getExtras().get("data");
             imageView.setImageBitmap(bitmap);
        }
    }
}