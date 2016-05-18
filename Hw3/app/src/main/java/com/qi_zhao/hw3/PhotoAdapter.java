package com.qi_zhao.hw3;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Qi on 5/17/2016.
 */
public class PhotoAdapter extends CursorAdapter {
    public PhotoAdapter(Context context, Cursor cursor, int flags) {
        super(context, cursor, flags);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.list_detailed, parent, false);
    }
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        String caption = cursor.getString(cursor.getColumnIndex("caption"));
        String path = cursor.getString(cursor.getColumnIndex("path"));

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = timeStamp + ".jpg";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        String tbpath = storageDir.getAbsolutePath() + "/" + imageFileName;


        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8; // Experiment with different sizes
        generateThumbnail(path, tbpath);
        Bitmap b = BitmapFactory.decodeFile(tbpath, options);
        ((TextView)view.findViewById(R.id.textView2)).setText(caption);
        ((ImageView)view.findViewById(R.id.imageView2)).setImageBitmap ( b );
    }
    static public void generateThumbnail(String imgFile, String thumbFile) {
        try {
            Bitmap picture = BitmapFactory.decodeFile(imgFile);
            Bitmap resized = ThumbnailUtils.extractThumbnail(picture, 120, 120);
            FileOutputStream fos = new FileOutputStream(thumbFile);
            resized.compress(Bitmap.CompressFormat.JPEG, 90, fos);
            fos.flush();
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
