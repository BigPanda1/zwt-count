package com.zjy.zwtcount.controller;

import com.zjy.zwtcount.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import net.coobird.thumbnailator.Thumbnails;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @Date 2020/5/20 15:22
 * @Created by zjy
 */
@Slf4j
@RestController
@RequestMapping("/img")
public class ImgController {

    /**
     * 图片格式转换压缩并返回base64 (图片大于200k)
     * @param file
     * @return
     */
//    @RequestMapping(value = "/imgFactory",
//            method = RequestMethod.POST,
//            produces = MediaType.APPLICATION_JSON_UTF8_VALUE,
//            consumes = MediaType.MULTIPART_FORM_DATA_VALUE
//    )
    @PostMapping("/imgFactory")
    public String  imgFactory(@RequestParam("file")MultipartFile file) throws Exception {
//        String extension = this.getFileExtension(file);//文件格式
        String filename = file.getOriginalFilename();//文件名+文件格式
        File imgFile = HttpClientUtil.multipartFileToFile(file);//图片文件流
        BufferedImage im = ImageIO.read(imgFile);
        String outPath = "C:\\Users\\zjy23\\Desktop\\政务通\\互联互通\\tmp_file\\"+System.currentTimeMillis()+".jpg";
        File dest = new File(outPath);
        ImageIO.write(im, "jpg", dest);
        double imgsize = dest.length()/1024.0;
        log.info("输出图片的路径：{}，图片大小：{}",outPath,imgsize);
        if( imgsize > 5 &&  imgsize < 200){
            String base64 = getImgBase64(outPath);
            return base64;
        }
        //压缩处理
        Thumbnails.of(imgFile).size(358,441).toFile(outPath);
        return getImgBase64(outPath);
    }

    private String getFileExtension(MultipartFile cFile) {
        String originalFileName = cFile.getOriginalFilename();
        return originalFileName.substring(originalFileName.lastIndexOf(".")+1);
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
}
