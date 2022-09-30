package com.jsa.analytics.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.Shape;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import androidx.annotation.ColorInt;

public class Utilities {

    public static boolean isValidEmail(CharSequence target) {
        if (TextUtils.isEmpty(target)) {
            return false;
        } else {
            return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
        }
    }

    public static Drawable getTextDrawable(Context context,@ColorInt int iColor,String inputText,int size) {
        Shape shape = new Shape(){

            @Override
            public void draw(Canvas canvas, Paint paint)
            {
                paint.setColor(iColor);

                paint.setTextSize(50);

//                int radii = dpToPx(context,3);
                canvas.drawText(inputText,size,size,paint);

//                canvas.drawText(inputText, canvas.getWidth() - 150, canvas.getHeight() / 2, paint);

//                canvas.drawCircle(canvas.getWidth() - radii * 2, canvas.getHeight() /2 - radii, radii, paint);
            }
        };
        shape.getHeight();

        Drawable drawable = new ShapeDrawable(shape);

        return drawable;
    }

    public BitmapDrawable writeOnDrawable(Context context,int drawableId, String text){

        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), drawableId).copy(Bitmap.Config.ARGB_8888, true);

        Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLACK);
        paint.setTextSize(20);

        Canvas canvas = new Canvas(bm);
        canvas.drawText(text, 0, bm.getHeight()/2, paint);

        return new BitmapDrawable(bm);
    }

    public static int dpToPx(Context context , int dp) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public static int pxToDp(Context context ,int px) {
        DisplayMetrics displayMetrics = context.getResources().getDisplayMetrics();
        return Math.round(px / (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }
}
