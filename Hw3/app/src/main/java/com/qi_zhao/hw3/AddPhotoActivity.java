package com.qi_zhao.hw3;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Qi on 5/15/2016.
 */
public class AddPhotoActivity extends AppCompatActivity {

    public SQLiteDatabase db;//PhotoInformation
    String fileName;
    final int requestCode=1234;
    Button take;
    Button save;
    EditText cap;
    private Uri imageUri;
    private String pictureImagePath = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addphoto);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        take = (Button) findViewById(R.id.button);
        save = (Button) findViewById(R.id.button2);
        cap = (EditText) findViewById(R.id.editText);

        if (take != null) {
            take.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                    if (intent.resolveActivity(getPackageManager()) == null) {
                        Toast.makeText(getApplicationContext(), "Cannot take pictures on this device!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    try {
                        File photoFile = createImFile();
                    } catch (IOException ex) {
                        Log.e("Error", "Error occurred");
                    }
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);

                    startActivityForResult(intent, 1);
                }
            });
            save.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    addPhoto();
                }
            });
        }


    }

    private File createImFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = timeStamp + ".jpg";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        pictureImagePath = storageDir.getAbsolutePath() + "/" + imageFileName;

        File im = new File(pictureImagePath); 
        imageUri = Uri.fromFile(im);


        return im;
    }


    private void addPhoto() {
        String caption = cap.getText().toString();
        File imgFile = new File(pictureImagePath);
        String path = imgFile.getAbsolutePath().toString();

        if (!path.equals("/")) {
            db = this.openOrCreateDatabase("hw3", MODE_PRIVATE, null);
            db.execSQL("INSERT INTO PhotoInformation (caption, path) VALUES ('" + caption + "', '" + path + "');");
            finish();
        }
    }



}
