package com.zjy.zwtcount.utils;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

/**
 * 社保加密工具类
 * Created by liwan on 2020/3/5.
 */
public class AesUtils {


    /**
     * 加密、解密key.
     */
    private static final String PASSWORD_CRYPT_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCydzR0CjU4gGU6ISSwB2+q6rWVq89RYWujKANHggT8dJ0vywgj1bFUF/ow22Zq589b6kZsnpmkRSEuTtbVF75VjoG4qBH+3jDfmtEqVOvSbXB9IDSmJl18fdh3FAL+VZAc3wwZRbtAOXo4oD7r936QobQx+FbY+/ZCOsZ3UMA3ywIDAQAB";


    /**
     * 加密
     * @param content
     * @return
     */
    public static String encrypt(String content) {
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");//构造密钥生成器，指定为AES算法，不区分大小写
            SecureRandom random=null;
            try {
                random = SecureRandom.getInstance("SHA1PRNG","SUN");
            } catch (NoSuchProviderException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
            random.setSeed(PASSWORD_CRYPT_KEY.getBytes());
            kgen.init(128, random);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec);//ENCRYPT_MODE指加密操作
            byte[] byteRresult = cipher.doFinal(byteContent);
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < byteRresult.length; i++) {
                String hex = Integer.toHexString(byteRresult[i] & 0xFF);
                if (hex.length() == 1) {
                    hex = '0' + hex;
                }
                sb.append(hex.toUpperCase());
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解密
     * @param content
     * @return
     */
    public static String decrypt(String content) {
        if (content.length() < 1)
            return null;
        byte[] byteRresult = new byte[content.length() / 2];
        for (int i = 0; i < content.length() / 2; i++) {
            int high = Integer.parseInt(content.substring(i * 2, i * 2 + 1), 16);
            int low = Integer.parseInt(content.substring(i * 2 + 1, i * 2 + 2),16);
            byteRresult[i] = (byte) (high * 16 + low);
        }
        try {
            KeyGenerator kgen = KeyGenerator.getInstance("AES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(PASSWORD_CRYPT_KEY.getBytes());
            kgen.init(128, secureRandom);
            SecretKey secretKey = kgen.generateKey();
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec secretKeySpec = new SecretKeySpec(enCodeFormat, "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);//Decrypt_mode指解密操作
            byte[] result = cipher.doFinal(byteRresult);
            return new String(result,"utf-8");//不加utf-8，中文时会乱码
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String mi = "6AEFB1DC92FF395DD719D6EACFE3E0798D826A0BD7DAA4F2C801FC8F4D1042AD8269FD92E7031D1A0348E62CDDE06160686627724B5A9B15F9B84CF8DCC67E9B543E6C1433E06A89B54EFFFB6412D505A2B280EC26318A1D2AADA426907E2C05F583D60187151D7CAE234CB3698E633DAC565E6C10C46A6CD35EF2369879AAB72CF962B39B4CA975D6CE616EAADB639EEDDFECBED9A5316D54DF51ACF1814C6757B05F3955D1890123E32F6CED2DA5AE3B8FC3E55CDF7473F0C077B3E9C02DAF";
        String nmsl ="C23F118AE5DF3B5E8F4EC5C71338D847";
        //String mi = encrypt(rezult);
        System.out.println("加密值：" + mi);
        System.out.println("解密值：" + decrypt(mi));
    }
}
