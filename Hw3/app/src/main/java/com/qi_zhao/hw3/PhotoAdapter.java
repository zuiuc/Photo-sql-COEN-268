package com.qi_zhao.hw3;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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
        //String tb = cursor.getString(cursor.getColumnIndex("tb"));
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8; // Experiment with different sizes
        Bitmap b = BitmapFactory.decodeFile(path, options);

        ((TextView)view.findViewById(R.id.textView2)).setText(caption);
        ((ImageView)view.findViewById(R.id.imageView2)).setImageBitmap ( b );
    }

}
