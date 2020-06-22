package com.zjy.zwtcount.service;

import com.zjy.zwtcount.entity.Cvs;

import java.io.FileNotFoundException;
import java.util.List;

/**
 * @Date 2020/4/1 9:53
 * @Created by zjy
 */
public interface AppService {

    List<Cvs> selAll();

    /**
     *
     * @param path 文件路径
     * @param sheetName 工作表名称
     * @param cellnum 读取的列
     * @param updatecellnum 修改的列
     */
    void getStatistics(String path,String sheetName,int cellnum,int updatecellnum) throws Exception;

    void readFileAndWriteDatabase(String saveFilepath2,Integer nummber,Integer url) throws FileNotFoundException;
}
