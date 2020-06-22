package com.zjy.zwtcount.img;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.makers.ThumbnailMaker;
import org.apache.commons.codec.binary.Base64;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;

/**
 * @Date 2020/5/19 15:41
 * @Created by zjy
 */
public class ImageFactory {

    public static void main(String[] args) {
        //图片统一转为jpg，压缩至5-200K 返回base64
        String filePath = "C:\\Users\\zjy23\\Desktop\\IMG_20200520_145308.png";
        String outPath = "C:\\Users\\zjy23\\Desktop\\政务通\\互联互通\\tmp_file\\"+System.currentTimeMillis()+".jpg";
//        String photoStr = "";
////        //计算base64图片的字节数(单位:字节)
//        Integer size = imageSize(photoStr);
//        System.out.println("字节 = "+size);
////        //把字节转换单位为kb或mb
//        String size2 = bytesToKB(size);
//        System.out.println("size2 = "+size2);
        String jpgAndCompression = toJpgAndCompression(filePath, outPath);
//        System.out.println(jpgAndCompression);


//        //压缩
//        try {
//            Thumbnails.of("C:\\Users\\zjy23\\Desktop\\IMG_20200520_145308.jpg").size(358,441 ).toFile("C:\\Users\\zjy23\\Desktop\\666666.jpg");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }


    private static String toJpgAndCompression(String filePath,String out){
        try {
            long start = System.currentTimeMillis();
            File read =new File(filePath);
//            BufferedImage im = ImageIO.read(read);
//            File dest = new File(out);
//            ImageIO.write(im, "jpg", dest);
//            double imgsize = dest.length()/1024.0;
//            if( imgsize > 5 &&  imgsize < 200){
//                String base64 = getImgBase64(out);
//                return base64;
//            }
            //压缩处理
//            Thumbnails.of(filePath).size(358,441).toFile(out);
            Thumbnails.of(read)//压缩尺寸 范围（0.00--1.00）
                    .outputQuality(0.50f)       //压缩质量 范围（0.00--1.00）
                    .size(358,441)          //压缩大小
                    .outputFormat("jpg")           //输出图片后缀
                    .toFile(out);                 //输出路径
            String imgBase64 = getImgBase64(out);
            long end = System.currentTimeMillis();
            double timeDouble= Double.parseDouble(Long.toString(end-start));
            System.out.println("该方法执行时间为" + timeDouble+ "毫秒，即" + timeDouble/(double)1000 + "秒");
            return imgBase64;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String getImgBase64(String imgFile) {
        // 将图片文件转化为字节数组字符串，并对其进行Base64编码处理
        InputStream in = null;
        byte[] data = null;
        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new String(Base64.encodeBase64(data));
    }

    //计算base64图片的字节数(单位:字节)
    //传入的图片base64是去掉头部的data:image/png;base64,字符串
    public static Integer imageSize(String imageBase64Str){

        //1.找到等号，把等号也去掉(=用来填充base64字符串长度用)
        Integer equalIndex= imageBase64Str.indexOf("=");
        if(imageBase64Str.indexOf("=")>0) {
            imageBase64Str=imageBase64Str.substring(0, equalIndex);
        }
        //2.原来的字符流大小，单位为字节
        Integer strLength=imageBase64Str.length();
        System.out.println("imageBase64Str Length = "+strLength);
        //3.计算后得到的文件流大小，单位为字节
        Integer size=strLength-(strLength/8)*2;
        return size;
    }

    /**
     * byte(字节)根据长度转成kb(千字节)和mb(兆字节)
     *
     * @param bytes
     * @return
     */
    public static String bytesToKB(long bytes) {
        BigDecimal filesize = new BigDecimal(bytes);
        BigDecimal megabyte = new BigDecimal(1024 * 1024);
        float returnValue = filesize.divide(megabyte, 1, BigDecimal.ROUND_DOWN).floatValue();
        if (returnValue > 1) {
            return (returnValue + "MB");
        }

        BigDecimal kilobyte = new BigDecimal(1024);
        returnValue = filesize.divide(kilobyte, 1, BigDecimal.ROUND_DOWN).floatValue();
        return (returnValue + "KB");
    }
}
