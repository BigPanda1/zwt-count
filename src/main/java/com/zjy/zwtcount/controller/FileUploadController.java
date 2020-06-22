package com.zjy.zwtcount.controller;

import com.zjy.zwtcount.entity.People;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/file")
public class FileUploadController {

    @PostMapping("/upload")
    public String upload(MultipartFile[] files,People people){
        if (files != null && files.length>0 ){
            for (MultipartFile file : files) {
                String filename = file.getOriginalFilename();
                System.out.println(filename);
            }
        }
        System.out.println(people);
        return "成功";
    }
}
