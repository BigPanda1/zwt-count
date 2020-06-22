package com.zjy.zwtcount.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;

public class CopyFile {
    public static void copy(String srcPathStr, String desPathStr) {
        //获取源文件的名称
        String newFileName = srcPathStr.substring(srcPathStr.lastIndexOf("\\")+1); //目标文件地址
        System.out.println("源文件:"+newFileName);
        desPathStr = desPathStr + File.separator + "APP打点统计-" + DateConverUtil.formatDateToStr(new Date(),"yyyyMMdd") + ".xls";
        System.out.println("目标文件地址:"+desPathStr);
        try
		{
             FileInputStream fis = new FileInputStream(srcPathStr);//创建输入流对象
             FileOutputStream fos = new FileOutputStream(desPathStr); //创建输出流对象               
             byte datas[] = new byte[1024*8];//创建搬运工具
             int len = 0;//创建长度   
             while((len = fis.read(datas))!=-1)//循环读取数据
			{
				fos.write(datas,0,len);
            } 
                fis.close();//释放资源
                fis.close();//释放资源
        }
			catch (Exception e)
			{
                e.printStackTrace();
            }
    }
    public static void main(String[] args)
	{     
        String srcPathStr = "E:\\APP打点统计.xls"; //源文件地址
		String desPathStr = "E:\\穗好办打点计数器"; //目标文件地址
        copy(srcPathStr, desPathStr);//将E:\\java task\\zhl.txt文件拷贝到E:\\java task\\zhlll
    } 
}
