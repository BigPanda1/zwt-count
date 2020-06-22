package com.zjy.zwtcount.service.impl;

import com.zjy.zwtcount.entity.Cvs;
import com.zjy.zwtcount.mapper.AppMapper;
import com.zjy.zwtcount.service.AppService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2020/4/1 9:53
 * @Created by zjy
 */
@Service
@Slf4j
public class AppServiceImpl implements AppService {

    @Autowired
    private AppMapper appMapper;

    @Override
    public List<Cvs> selAll() {
        return appMapper.selAll();
    }

    @Override
    public void getStatistics(String path, String sheetName, int cellnum, int updatecellnum) throws Exception {
        List<Cvs> list = this.selAll();
        for (Cvs cvs:list) {//去除cvs接口？之后不必要参数
            if(cvs.getUrl().contains("?")){
                cvs.setUrl(cvs.getUrl().substring(0,cvs.getUrl().indexOf("?")));
            }
        }
        log.info("当日查询的接口访问量条数：{}", list.size());
        for (Cvs cvs : list) {
            this.readExcelData(path, sheetName, cellnum, updatecellnum, cvs);
        }
    }

    /**
     * @param fielName      文件路径
     * @param sheetName     工作表名称
     * @param readCellnum       读取的列
     * @param updateCellnum 修改的列
     */

    public static void readExcelData(String fielName, String sheetName, int readCellnum, int updateCellnum, Cvs cvs) throws Exception {
        String fileToBeRead = fielName; // excel位置
        int readcoloum = readCellnum; // 比如你要获取第1列
        int writecoloum = updateCellnum; // 比如读取的列
        try {
            HSSFWorkbook workbook = new HSSFWorkbook(new FileInputStream(
                    fileToBeRead));
            HSSFSheet sheet = workbook.getSheet(sheetName);
            for (int i = 0; i <= sheet.getLastRowNum(); i++) {
                HSSFRow row = sheet.getRow((short) i);
                if (null == row) {
                    continue;
                } else {
                    HSSFCell writecell = row.getCell((short) writecoloum);
                    HSSFCell readcell = row.getCell((short) readcoloum);
                    if (null == readcell) {
                        continue;
                    } else {
                        if(readcell.getCellType() == Cell.CELL_TYPE_STRING){
                            if(readcell.getStringCellValue().equals(cvs.getUrl())){
                                log.info("读写的接口：{}，读取修改的单元格所在的行：{}，单元格所在的行值：{}，写入的数据：{}",
                                        cvs.getUrl(),writecell.getRowIndex()+1,writecell.getNumericCellValue(),writecell.getNumericCellValue()+cvs.getVolume());
                                writecell.setCellValue(writecell.getNumericCellValue()+cvs.getVolume());
                            }
                        }

                    }
                }
            }
            FileOutputStream out = null;
            try {
                out = new FileOutputStream(fileToBeRead);
                workbook.write(out);
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void readFileAndWriteDatabase(String saveFilepath2, Integer nummber, Integer url) throws FileNotFoundException {
        List<Cvs> list =new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(saveFilepath2));//换成你的文件名
//            reader.readLine();//第一行信息，为标题信息，不用,如果需要，注释掉
            String line = null;
            Cvs cvs = null;
            while((line=reader.readLine())!=null){
                String item[] = line.split(",");//CSV格式文件为逗号分隔符文件，这里根据逗号切分
                Integer volume = Integer.parseInt(item[nummber]);
                String urls = item[url];
                log.info("cvs读取的访问量：{}，url:{}",volume,urls);
                cvs = new Cvs();
                cvs.setVolume(volume);
                cvs.setUrl(urls);
                list.add(cvs);
            }
            //插入数据库
            appMapper.addListCvs(list);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }


}
