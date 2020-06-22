package com.zjy.zwtcount;

import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.SCPClient;
import com.zjy.zwtcount.mapper.AppMapper;
import com.zjy.zwtcount.service.AppService;
import com.zjy.zwtcount.utils.CopyFile;
import com.zjy.zwtcount.utils.DateConverUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;




@SpringBootTest
@Slf4j
class ZwtCountApplicationTests {
    @Autowired
    private AppService service;

    @Autowired
    private AppMapper mapper;


    private final String downLoadFile = "/home/app/script/output.csv"; //服务器上文件的路径
    private final String saveFilepath = "D:\\test";    //下载带本地的文件路径
    private final String saveFilepath2 = "D:\\test\\output.csv"; //下载带本地的文件路径
    private final String writeFile = "D:\\test\\APP打点统计-" + DateConverUtil.formatDateToStr(new Date(),"yyyyMMdd") + ".xls"; //写入的xls表格的路径
//    private final String writeFile = "E:\\穗好办打点计数器\\APP打点统计-20200416-前20项.xls"; //写入的xls表格的路径


    private final String srcPathStr = "D:\\test\\template\\APP打点统计模板.xls"; //读取源文件地址
    private final String desPathStr = "D:\\test"; //写入目标文件地址

    /**
     * 下载linux 的 output.csv
     * @throws IOException
     */
    @Test
    public void manage() throws Exception {
        String downloadIP = "14.152.49.69";
        String downloadRemoteUser = "root";
        String downloadRemotePass = "&^S*iyu$xinxi20!06";
        Connection con = new Connection(downloadIP, 22);
        con.connect();
        boolean isAuthed = con.authenticateWithPassword(downloadRemoteUser, downloadRemotePass);
        if (isAuthed) {
            SCPClient scpClient = con.createSCPClient();
            scpClient.get(downLoadFile, saveFilepath);
            log.info("下载完成!");
            //清空数据
            mapper.delAll();
            log.info("数据清空完成!");
            //读取文件写入数据库
            service.readFileAndWriteDatabase(saveFilepath2,0,1);
            //写入文件
            CopyFile.copy(srcPathStr,desPathStr);
            service.getStatistics(writeFile,"x月10日", 3,  4);
            log.info("数据写入表格完成");
        } else {
            System.out.println("数据写入表格失败");
        }
    }
    /**
     * 清空mysql表数据
     */
    @Test
    public void delAll(){
        mapper.delAll();
        System.out.println("数据清空完成！");
    }

    @Test
    public void statistics() throws Exception {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\zjy23\\Desktop\\output.csv"));//换成你的文件名
//            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
            String line = null;
            while((line=reader.readLine())!=null){
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                Integer volume = Integer.parseInt(item[0]);
                String url = item[1];
                //int value = Integer.parseInt(last);//如果是数值，可以转化为数值
                System.out.println("volume--->"+volume+",url--->"+url);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
