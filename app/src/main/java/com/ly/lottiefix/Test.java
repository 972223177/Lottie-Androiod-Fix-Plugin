package com.ly.lottiefix;

import android.graphics.Bitmap;

public class Test {
    Bitmap bitmapForId(String id) {
        try {
            return Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        } catch (Exception e) {
            return null;
        }
    }
}
