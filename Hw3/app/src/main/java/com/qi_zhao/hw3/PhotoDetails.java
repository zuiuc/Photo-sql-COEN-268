package com.qi_zhao.hw3;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Qi on 5/17/2016.
 */
public class PhotoDetails extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.photo_details);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        TextView textView = (TextView) findViewById(R.id.textView);
        ImageView imageView = (ImageView) findViewById(R.id.imageView);

        Bundle bundle = new Bundle();
        bundle = getIntent().getExtras();

        // set textView to show caption
        textView.setText(bundle.getString("currentCaption"));

        // set imageView to show image
        String filePath = bundle.getString("currentPath");
        Bitmap yourSelectedImage = BitmapFactory.decodeFile(filePath);
        Drawable d = new BitmapDrawable(yourSelectedImage);
        imageView.setImageDrawable(d);
    }

}

