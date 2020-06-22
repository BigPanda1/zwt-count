package com.zjy.zwtcount.controller;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import com.zjy.zwtcount.mapper.AppMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * @Date 2020/4/9 10:21
 * @Created by zjy
 */
@RestController
public class ZWTController {

    @Autowired
    private AppMapper mapper;

    @GetMapping("/upload")
    public String uploadFile() throws IOException {
        String downloadIP = "14.152.49.69";
        String downloadRemoteUser = "root";
        String downloadRemotePass = "!@#$%qwert";
        Connection con = new Connection(downloadIP, 22);
        con.connect();
        boolean isAuthed = con.authenticateWithPassword(downloadRemoteUser, downloadRemotePass);
        if (isAuthed) {
            SCPClient scpClient = con.createSCPClient();
            scpClient.get("/home/app/script/output.csv", "/root/");
            mapper.delAll();
        } else {
            System.out.println("登陆失败!");
        }
        return  "";
    }


}
