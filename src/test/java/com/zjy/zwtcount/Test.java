package com.zjy.zwtcount;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Test {
    public static void main3(String[] args) {
//        String msg = "您的服务银行是建设银行，您已于2020年05月26日办理金融蓝卡补卡业务，选择的领卡网点是建设银行广州太古汇支行，但尚未缴纳补换领社会保障卡工本费。为确保新卡按期制发，请您尽快完成缴费。非税缴费编号：20008077225，详见\\u003ca target\\u003d\\u0027_blank\\u0027 href\\u003d\\\"http://rsj.gz.gov.cn/ywzt/shbz/shbzsmk/gzssbkbmwd/content/post_2419190.html\\\"\\u003e非税缴费指引\\u003c/a\\u003e。";
//        System.out.println(msg.indexOf("非税缴费编号"));
//        System.out.println(msg.indexOf("详见"));
//        String substring = msg.substring(msg.indexOf("非税缴费编号"), msg.indexOf("详见"));
//        System.out.println(substring);
//        String regEx="[^0-9]";
//        Pattern p = Pattern.compile(regEx);
//        Matcher m = p.matcher(substring);
////        System.out.println( m.replaceAll("").trim());
//        String photoStr = "=";
//        //计算base64图片的字节数(单位:字节)
//        Integer size = imageSize(photoStr);
//        System.out.println("字节 = "+size);
//        //把字节转换单位为kb或mb
//        String size2 = bytesToKB(size);
//        System.out.println("size2 = "+size2);
//        String area = "广东省,广州市,黄埔区";
//        String address = "广州经济技术开发区永和镇贤江路9号";
//        String result = "";
////        System.out.println(area.replaceAll(",",""));
//        if(area.contains(",")){
//            result = area.replaceAll(",", "");
//        }else{
//            result = area;
//        }
//        result = result+ address;
//        System.out.println(result);



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



    public static void main(String[] args) {
        int size=5;//菱形层数
        for (int i = 1; i <= size; i++) {
            for (int j = 1; j <= size - i; j++) {
                System.out.print(" ");
            }
            for (int k = 1; k <= 2 * i - 1; k++) {
                if(k % 2 == 0){
                    System.out.print(' ');
                    continue;
                }
                System.out.print('*');
            }
            System.out.println();
        }
        for (int i = 1; i <= size-1; i++) {
            for (int j = 1; j <= i; j++){
                System.out.print(" ");
            }
            for (int k = 2*size-3; k >= 2 * i - 1; k--){
                if(k % 2 == 0){
                    System.out.print(' ');
                    continue;
                }
                System.out.print('*');
            }
            System.out.println();
        }
    }

}

