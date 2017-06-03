package com.weirdotech.payticket.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by Bingo on 17/6/2.
 */
public class PicUtils {

    /**
     * 把Bitmap转Byte
     */

    public static byte[] bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        return baos.toByteArray();
    }

    /**
     * 将字节数组转换为ImageView可调用的Bitmap对象
     *
     * @param bytes
     * @param opts
     * @return Bitmap
     */
    public static Bitmap getPicFromBytes(byte[] bytes,
                                         BitmapFactory.Options opts) {
        if (bytes != null)
            if (opts != null) {
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
                        opts);
            } else {
                return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            }
        return null;
    }
}
