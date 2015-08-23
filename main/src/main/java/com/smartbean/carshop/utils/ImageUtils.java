package com.smartbean.carshop.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Administrator on 2015/8/20.
 */
public class ImageUtils {
    public static Bitmap getHttpBitmap(String url){
        URL myFileURL;
        Bitmap bitmap=null;
        try{
            url = url.replaceAll("\\\\", "/");
            myFileURL = new URL(url);

            HttpURLConnection conn=(HttpURLConnection)myFileURL.openConnection();
            InputStream is = conn.getInputStream();
            bitmap = BitmapFactory.decodeStream(is);
            is.close();
        }catch(Exception e){
            e.printStackTrace();
        }

        return bitmap;
    }
}
