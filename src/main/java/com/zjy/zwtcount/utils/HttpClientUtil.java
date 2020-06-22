package com.zjy.zwtcount.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.HashMap;

@Slf4j
public class HttpClientUtil {
    /**
     * MultipartFile 转 File
     * @param file
     * @throws Exception
     */
    public static File multipartFileToFile( @RequestParam MultipartFile file) throws Exception {

        File toFile = null;
        if(file.equals("")||file.getSize()<=0){
            file = null;

        }else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File("C:\\Users\\zjy23\\Desktop\\政务通\\互联互通\\tmp_file\\"+file.getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }
    public static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        File file = new File("xxxxxxxxxx");
        HttpClientUtil httpClient = new HttpClientUtil();
        HashMap<String, File> files = new HashMap<>();
        HashMap<String, Object> datas = new HashMap<>();
        HashMap<String, String> headers = new HashMap<>();
        String url = "xxxxxxx";
        // headers.put("Content-Type", "multipart/form-data");
        headers.put("Authorization", "xxxxxx");
        datas.put("xxxxx", "xxxxx");
        files.put("file", file);
//        String result = httpClient.request(url, datas, files, headers);
//        System.out.println(result);
    }
}
