package com.zjy.zwtcount;


import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * AES256加解密工具类
 */
public class AES256Util {
    private static final Logger logger = LoggerFactory.getLogger(AES256Util.class);


    public static String encryptWithAES(String info, String aeskey) {
        try {
            byte[] key = HexUtil.hexStr2ByteArray(aeskey);
            byte[] bytes = info.getBytes();
            byte[] encrypt = AES256Coder.encrypt(bytes, key);
            return HexUtil.byteArray2HexStr(encrypt);
        } catch (Exception e) {
            logger.error("encrypt执行失败： " + e.getMessage(), e);
        }
        return null;
    }

    public static String decryptWithAES(String info, String aeskey) {
        try {
            byte[] key = HexUtil.hexStr2ByteArray(aeskey);
            byte[] bytes = HexUtil.hexStr2ByteArray(info);
            byte[] decrypt = AES256Coder.decrypt(bytes, key);
            return new String(decrypt);
        } catch (Exception e) {
            logger.error("decrypt执行失败： " + e.getMessage(), e);
        }
        return null;
    }

    public static void main(String[] args) {
        long timeMillis = System.currentTimeMillis();
        String client_secret = "7de83b154bf242ac8b0ec0a5d3400afb";
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("clientId","shb001");
        jsonObject.put("clientSecret","7de83b154bf242ac8b0ec0a5d3400afb");
        jsonObject.put("time",System.currentTimeMillis());
        EncrytDto dto =new EncrytDto();
        dto.setClientId("shb001");
        dto.setClientSecret("7de83b154bf242ac8b0ec0a5d3400afb");
        dto.setTime(timeMillis);
        String string = JSONObject.toJSONString(dto);
        String withAES = AES256Util.encryptWithAES(string, client_secret);
        System.out.println("-------->加密："+withAES);
        String decryptWithAES = AES256Util.decryptWithAES(withAES, client_secret);
        System.out.println("--------->解密:" + decryptWithAES);


        String info = jsonObject.toJSONString();
        String encryptInfo = AES256Util.encryptWithAES(info, client_secret);
        System.out.println("====encryptInfo:" + encryptInfo);

        String decryptInfo = AES256Util.decryptWithAES(encryptInfo, client_secret);
        System.out.println("====decryptInfo:" + decryptInfo);

        System.out.println("====decryptInfo==info:" + info.equals(decryptInfo));
    }

}
