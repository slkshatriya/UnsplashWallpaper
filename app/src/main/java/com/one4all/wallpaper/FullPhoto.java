package com.one4all.wallpaper;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;

public class FullPhoto extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_photo);
        String imageUrl = getIntent().getStringExtra("imageUrl");
        imageView = findViewById(R.id.full_photo);
        Picasso.get().load(imageUrl).into(imageView);
        Log.d("hello", "onCreate: "+imageUrl);
    }

}
