package com.appmerise.foodsy;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by root on 23/3/18.
 */
public class PicassoClient {

    public static void downloadimg(Context c, String url, ImageView img) {
        if (url != null && url.length() > 0) {
            Picasso.with(c).load(url).placeholder(R.drawable.apple).into(img);

        } else {
            Picasso.with(c).load(R.drawable.apple).into(img);
        }
    }

}
