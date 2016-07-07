package com.qianmi.baselibrary.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Environment;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by su on 2015/7/8.
 */
public class FileUtil {

    private static final String TAG = "FileUtil";

    public static final String LOCAL = "shine";
    public static final String IMAGES = "images";
    public static final String REC = "REC";

    public static final String LOCAL_PATH = Environment.getExternalStorageDirectory().getPath() + File.separator + LOCAL + File.separator;

    /**
     * 录音文件目录
     */
    public static final String REC_PATH = LOCAL_PATH + REC + File.separator;

    /**
     * 图片目录
     */
    public static final String IMAGE_PATH = LOCAL_PATH + IMAGES + File.separator;


    /**
     * 自动在SD卡创建相关的目录
     */
    static {
        File dirRootFile = new File(LOCAL_PATH);
        if (!dirRootFile.exists()) {
            dirRootFile.mkdirs();
        }

        File dirFile = new File(IMAGE_PATH);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }

        File recFile = new File(REC_PATH);
        if (!recFile.exists()) {
            recFile.mkdirs();
        }
    }

    /**
     * 判断是否存在存储空间	 *
     *
     * @return
     */
    public static boolean isExitSDCard() {
        return Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED);
    }

    private static boolean hasFile(String fileName) {
        File f = createFile(fileName);
        return null != f && f.exists();
    }

    public static File createFile(String fileName) {

        File myCaptureFile = new File(IMAGE_PATH + fileName);
        if (myCaptureFile.exists()) {
            myCaptureFile.delete();
        }
        try {
            myCaptureFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return myCaptureFile;
    }

    public static String getImageFile(String imageName) {

        File myCaptureFile = new File(IMAGE_PATH + imageName + ".jpg");
        if (!myCaptureFile.exists()) {
            try {
                myCaptureFile.createNewFile();
                return myCaptureFile.getAbsolutePath();
            } catch (IOException e) {
                e.printStackTrace();
                return "";
            }
        }
        return myCaptureFile.getAbsolutePath();
    }

    public static String processPicture(Bitmap bitmap) {
        ByteArrayOutputStream jpeg_data = new ByteArrayOutputStream();
        byte[] output = null;
        byte[] code = null;
        try {
            if (bitmap.compress(Bitmap.CompressFormat.JPEG, 50, jpeg_data)) {
                code = jpeg_data.toByteArray();
                output = Base64.encode(code, Base64.NO_WRAP);
                return new String(output);
            }
        } catch (Exception e) {
            return ("Error compressing image.");
        } finally {
            if (jpeg_data != null) {
                try {
                    jpeg_data.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            output = null;
            code = null;
            bitmap = null;
        }
        return "";
    }

    public static Bitmap getLocalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取压缩后图片的二进制数据
     * @param srcPath
     * @return
     */
    public static byte[] getCompressedImage(String srcPath) {
        if (!new File(srcPath).exists()){
            return null;
        }

        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        int w = newOpts.outWidth;
        int h = newOpts.outHeight;
        float hh = 800f;//这里设置高度为800f
        float ww = 480f;//这里设置宽度为480f
        //缩放比。由于是固定比例缩放，只用高或者宽其中一个数据进行计算即可
        int be = 1;//be=1表示不缩放
        if (w > h && w > ww) {//如果宽度大的话根据宽度固定大小缩放
            be = (int) (newOpts.outWidth / ww);
        } else if (w < h && h > hh) {//如果高度高的话根据宽度固定大小缩放
            be = (int) (newOpts.outHeight / hh);
        }
        if (be <= 0) {
            be = 1;
        }
        newOpts.inSampleSize = be;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        bitmap = BitmapFactory.decodeFile(srcPath, newOpts);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 300) {    //循环判断如果压缩后图片是否大于300kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 15;//每次都减少15
            bitmap.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中
        }
        return baos.toByteArray();
    }

    public static Bitmap compressImage(Bitmap image) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, baos);//质量压缩方法，这里100表示不压缩，把压缩后的数据存放到baos中
        int options = 100;
        while (baos.toByteArray().length / 1024 > 300) {    //循环判断如果压缩后图片是否大于300kb,大于继续压缩
            baos.reset();//重置baos即清空baos
            options -= 15;//每次都减少15
            image.compress(Bitmap.CompressFormat.JPEG, options, baos);//这里压缩options%，把压缩后的数据存放到baos中

        }
        ByteArrayInputStream isBm = new ByteArrayInputStream(baos.toByteArray());//把压缩后的数据baos存放到ByteArrayInputStream中
        return BitmapFactory.decodeStream(isBm, null, null);//把ByteArrayInputStream数据生成图片
    }

    public static Bitmap getImage(String srcPath) {
        BitmapFactory.Options newOpts = new BitmapFactory.Options();
        //开始读入图片，此时把options.inJustDecodeBounds 设回true了
        newOpts.inJustDecodeBounds = true;
//        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);//此时返回bm为空

        newOpts.inJustDecodeBounds = false;
        newOpts.inSampleSize = 5;//设置缩放比例
        //重新读入图片，注意此时已经把options.inJustDecodeBounds 设回false了
        Bitmap bitmap = BitmapFactory.decodeFile(srcPath, newOpts);
        if (bitmap != null) {
            return bitmap;//压缩好比例大小后再进行质量压缩
        } else {
            return null;
        }
    }

    /**
     * 保存二进制数据到sdcard文件
     * @param localFile 文件全路径
     * @param bytes
     */
    public static void save(String localFile, byte[] bytes) {
        try {
            File dirFile = new File(localFile.substring(0,localFile.lastIndexOf(File.separator)));
            if (!dirFile.exists()) {
                dirFile.mkdirs();
            }

            FileOutputStream outputStream = new FileOutputStream(new File(localFile));
            outputStream.write(bytes);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean checkRotationPhoto(String path) {
        Bitmap bitmap;
        int rotateDegree = CameraUtil.getExifOrientation(path);
        if (rotateDegree != 0) {
            bitmap = CameraUtil.rotateBitmapByDegree(FileUtil.getImage(path), rotateDegree);
        } else {
            return true;
        }
        return bitmap != null && saveMyBitmap(bitmap, path);
    }

    public static boolean addWatermarkBitmap(String path ,String ... str) {
        Bitmap bitmap;
        int rotateDegree = CameraUtil.getExifOrientation(path);
        if (rotateDegree != 0) {
            bitmap = CameraUtil.rotateBitmapByDegree(FileUtil.getImage(path), rotateDegree);
        } else {
            bitmap = getImage(path);

        }
        if (bitmap != null) {
            return  addWatermarkBitmap(bitmap, path, str);
        }
        return false;
    }

    private static final int page = 20;
    private static  boolean addWatermarkBitmap(Bitmap bitmap,String path ,String... str) {
        int destWidth = bitmap.getWidth();   //此处的bitmap已经限定好宽高
        int destHeight = bitmap.getHeight();
        Log.v("addWatermarkBitmap","width = " + destWidth+" height = "+destHeight);
        Bitmap icon = Bitmap.createBitmap(destWidth, destHeight, Bitmap.Config.ARGB_8888); //定好宽高的全彩bitmap
        Canvas canvas = new Canvas(icon);//初始化画布绘制的图像到icon上

        Paint photoPaint = new Paint(); //建立画笔
        photoPaint.setDither(true); //获取跟清晰的图像采样
        photoPaint.setFilterBitmap(true);//过滤一些

        Rect src = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());//创建一个指定的新矩形的坐标
        Rect dst = new Rect(0, 0, destWidth, destHeight);//创建一个指定的新矩形的坐标
        canvas.drawBitmap(bitmap, src, dst, photoPaint);//将photo 缩放或则扩大到 dst使用的填充区photoPaint

        Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DEV_KERN_TEXT_FLAG);//设置画笔
        textPaint.setTextSize(destWidth/30);//字体大小
        textPaint.setTextAlign(Paint.Align.CENTER);
        textPaint.setTypeface(Typeface.DEFAULT_BOLD);//采用默认的宽度
        textPaint.setAntiAlias(true);  //抗锯齿
        textPaint.setStrokeWidth(1);
        textPaint.setAlpha(115);
        textPaint.setStyle(Paint.Style.STROKE); //空心
        textPaint.setColor(Color.WHITE);//采用的颜色
        textPaint.setShadowLayer(1f, 0f, 1f, Color.LTGRAY);
        int i = 0;
        for (String s : str) {
            if(StrUtils.isNotNull(s)) {
                int lenCount = s.length() / page;
                if (lenCount > 0) {
                    int startIndex = i + 1;
                    for (int count = 0;count < lenCount;count++) {
                        canvas.drawText(s.substring(count*page,count*page+page), destWidth / 2, destHeight - 45 * (startIndex+lenCount-count), textPaint);//绘制上去字，开始未知x,y采用那只笔绘制
                        i++;
                    }
                    canvas.drawText(s.substring(lenCount*page, s.length()), destWidth / 2, destHeight - 45 * (startIndex), textPaint);//绘制上去字，开始未知x,y采用那只笔绘制
                }else {
                    i++;
                    canvas.drawText(s, destWidth / 2, destHeight - 45 * i, textPaint);//绘制上去字，开始未知x,y采用那只笔绘制
                }
            }
        }
        canvas.save(Canvas.ALL_SAVE_FLAG);
        canvas.restore();
        bitmap.recycle();
        return saveMyBitmap(icon,path); //保存至文件
//        return true;
    }



    //获取图片缩小的图片
    public static Bitmap scaleBitmap(String src,int max){
        //获取图片的高和宽
        BitmapFactory.Options options = new BitmapFactory.Options();
        //这一个设置使 BitmapFactory.decodeFile获得的图片是空的,但是会将图片信息写到options中
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(src, options);
        // 计算比例 为了提高精度,本来是要640 这里缩为64
        int be = max;
        if (be <= 0) {
            be = 1;
        }
        options.inSampleSize = be;
        //设置可以获取数据
        options.inJustDecodeBounds = false;
        //获取图片
        return BitmapFactory.decodeFile(src, options);
    }

    public static boolean saveMyBitmap(Bitmap bmp,String path){
        if (!isExitSDCard()) {
            return false;
        }
        FileOutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(new File(path));
            bmp.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.flush();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bmp != null) {
                bmp.recycle();
            }
        }
        return true;
    }

}
