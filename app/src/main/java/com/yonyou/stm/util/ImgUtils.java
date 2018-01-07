package com.yonyou.stm.util;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Build;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

/**
 * Created by atian on 2017/11/26.
 */

public class ImgUtils {

    public static String getCompressBase64(Activity ac, Uri uri,double size)
    {
        try {
            InputStream input = ac.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            input.close();
            bitmap = imageZoom(bitmap,size);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] bytes = baos.toByteArray();
            byte[] encode = Base64.encode(bytes, Base64.DEFAULT);
            return new String(encode);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static String  getBase64(Activity ac, Uri uri) {
        try {
            InputStream input = ac.getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(input);
            input.close();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            byte[] bytes = baos.toByteArray();
            byte[] encode = Base64.encode(bytes, Base64.DEFAULT);
            return new String(encode);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static Bitmap imageZoom(Bitmap bitMap, double size) {
        double mid = getBitmapsize(bitMap) / 1024;
        //判断bitmap占用空间是否大于允许最大空间  如果大于则压缩 小于则不压缩
        if (mid > size) {
            double i = mid / size;
            return zoomImage(bitMap, bitMap.getWidth() / Math.sqrt(i),
                    bitMap.getHeight() / Math.sqrt(i));
        }
        return bitMap;
    }

    public static long getBitmapsize(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            return bitmap.getByteCount();
        }
        return bitmap.getRowBytes() * bitmap.getHeight();

    }

    public static Bitmap imageZoomByWH(Bitmap bitMap, int minSize) {

        float ml = (bitMap.getWidth() > bitMap.getHeight()) ? bitMap.getHeight() : bitMap.getWidth();
        float scale = minSize / ml;
        Matrix matrix = new Matrix();
        matrix.postScale(scale, scale);
        return Bitmap.createBitmap(bitMap, 0, 0, bitMap.getWidth(), bitMap.getHeight(), matrix, false);
    }

    public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
                                   double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = false;
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }

    public static File imageZoom(File file , double size) {
        try {
            Bitmap bitmap=BitmapFactory.decodeFile(file.getAbsolutePath());
            Bitmap zoomBitmap = imageZoom(bitmap, size);
            final FileOutputStream out = new FileOutputStream(file);
            zoomBitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return file;
    }
}
