package com.zjy.zwtcount;

import com.github.pagehelper.util.StringUtil;
import com.zjy.zwtcount.utils.DocxUtils;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;

/**
 * @Date 2020/5/14 14:05
 * @Created by zjy
 */
public class jiuyiqueren {

    @Test
    public  void  jiuyiqueren() throws Exception {
        String readFile = "C:\\Users\\zjy23\\Desktop\\政务通\\互联互通\\模板文档\\广州市城乡居民社会医疗保险就医确认.docx";
        String writeFile = "C:\\Users\\zjy23\\Desktop\\政务通\\互联互通\\tmp_file\\9999999.docx";
        Map<String, String> map = new LinkedHashMap<>();
        map.put("${name}", "张纪元");
        map.put("${computerCode}", "13456789");
        map.put("${insuranceCode}", "610521");
        map.put("${phone}", "");
        map.put("${yunqi}", "");
        map.put("${jhsyfwzbh}", "");
        map.put("${spouseName}", "");
        map.put("${spouseIdCard}", "");
        map.put("${fjhospital}", "");
        map.put("${fmhospital}", "");
        map.put("${rlhospital}", "");
        map.put("${ydhospital}", "");
        map.put("${content}", "读取文件的位置：阿萨大俺的阿萨大爱国回到房间人口环境和咖啡馆纷纷倒戈二十的复合org发布官方答复v购票人让他脚后跟如果 而高房价的复活复活的房价肯定会分隔符地方进口的共和党和尽快更换嗯号了对方进攻领导机关和几分感慨更何况回家了飞机回国看如何管理法规和和 ");
        map.put("${applyName}", "张纪元");
        map.put("${date}", "2020年5月14日");
        DocxUtils.replaceVariableTable(readFile,writeFile,map,false);
    }

    @Test
    public void jihuashengyu() throws Exception {
        String readFile = "C:\\Users\\zjy23\\Desktop\\政务通\\互联互通\\模板文档\\计划生育奖励扶助对象申报表.docx";
        String writeFile = "C:\\Users\\zjy23\\Desktop\\政务通\\互联互通\\tmp_file\\10101010.docx";
        Map<String, String> map = new LinkedHashMap<>();
        map.put("${xm}","张纪元");
        map.put("${idCard}","610521200001051491");
        map.put("${sex}","男");
        map.put("${birthday}","2020-01-05");
        map.put("${mstatus}","未婚");
        map.put("${phone}","18329344212");
        map.put("${in_type}","市社保");
        map.put("${in_no}","1234567890");
        map.put("${hjdz}","北京市房山区松高路13号");
        map.put("${xjzdz}","北京市房山区松高路13号");
        map.put("${work_unit}","阿里巴巴");
        map.put("${work_type}","开发狗");
        map.put("${is_swqr}","否");
        map.put("${qr_time}","/");
        map.put("${help_money}","银行代发");
        map.put("${df_bank}","光大银行");
        map.put("${df_code}","123456789");
        map.put("${p_name}","/");
        map.put("${p_type}","/");
        map.put("${p_idcard}","/");
        map.put("${p_sex}","/");
        map.put("${p_bri}","/");
        map.put("${p_mstatus}","/");
        map.put("${p_work_unit}","/");
        map.put("${p_work_type}","/");
        map.put("${p_hjdz}","/");
        map.put("${p_xjzdz}","/");
        map.put("${t_v}","0");
        map.put("${t_n}","1");
        map.put("${z_v}","0");
        map.put("${z_n}","1");
        map.put("${x_v}","0");
        map.put("${x_n}","0");

        map.put("${xm1}","香香香");
        map.put("${xy1}","未知");
        map.put("${gs1}","未知");
        map.put("${zj1}","居民身份证");
        map.put("${hm1}","6105120001051491");
        map.put("${xb1}","");
        map.put("${cs1}","2000-01-05");
        map.put("${xk1}","健康");

        map.put("${xm2}","");
        map.put("${xy2}","");
        map.put("${gs2}","");
        map.put("${zj2}","");
        map.put("${hm2}","");
        map.put("${xb2}","");
        map.put("${cs2}","");
        map.put("${xk2}","");

        map.put("${xm3}","");
        map.put("${xy3}","");
        map.put("${gs3}","");
        map.put("${zj3}","");
        map.put("${hm3}","");
        map.put("${xb3}","");
        map.put("${cs3}","");
        map.put("${xk3}","");

        map.put("${xm4}","");
        map.put("${xy4}","");
        map.put("${gs4}","");
        map.put("${zj4}","");
        map.put("${hm4}","");
        map.put("${xb4}","");
        map.put("${cs4}","");
        map.put("${xk4}","");

        map.put("${xm5}","");
        map.put("${xy5}","");
        map.put("${gs5}","");
        map.put("${zj5}","");
        map.put("${hm5}","");
        map.put("${xb5}","");
        map.put("${cs5}","");
        map.put("${xk5}","");

        map.put("${xysx}","/");
        map.put("${dyjg}","广东省广州市越秀区");
        map.put("${dyr}","张纪元");
        map.put("${dysj}","2000-05-15 11:00:00");
        map.put("${yyyy}","2020");
        map.put("${mm}","05");
        map.put("${dd}","15");
        map.put("${zvsw_time}","");
        map.put("${zvsw_reason}","");
        map.put("${zvsw_unit}","");
        map.put("${zvsw_mark}","");
        map.put("${zvcj_time}","");
        map.put("${zvcj_type}","");
        map.put("${zvcj_level}","");
        map.put("${zvcj_unit}","");
        map.put("${zvcj_code}","");
        map.put("${bfz_time}","");
        map.put("${bfz_level}","");
        map.put("${bfz_unit}","");
        map.put("${bfz_code}","");
        map.put("${ss_time}","");
        map.put("${ss_way}","");
        map.put("${ss_unit}","");
        map.put("${ss_zmd}","");
        map.put("${ss_mark}","");

        Map<String,String> headerMap = new LinkedHashMap<>();
        headerMap.put("url","C:\\Users\\zjy23\\Pictures\\Screenshots\\1579516416627.jpeg");
        headerMap.put("type","jpeg");
        headerMap.put("filename","1579516416627.jpeg");
//        headerMap.put("width","100");
//        headerMap.put("height","120");
        FileInputStream inputStream = new FileInputStream(readFile);
        XWPFDocument document = new XWPFDocument(inputStream);
         List<XWPFTable> tables = document.getTables();
            replaceAllTexts(map,document.getParagraphs());
//                replaceInParagraphText(document,map);
        //替换表格
        for (XWPFTable tab : document.getTables()) {
            for (XWPFTableRow row : tab.getRows()) {
                for (XWPFTableCell cell : row.getTableCells()) {
                    for (XWPFParagraph p : cell.getParagraphs()) {
                        for (XWPFRun r : p.getRuns()) {
                            String text = r.getText(0);
                            for (String key : map.keySet()) {
                                if (text != null && text.contains(key)) {
                                    r.setText(map.get(text), 0);
                                }
                            }
                        }
                    }
                }
            }
        }
        //插入图片
        XWPFTable table0 = tables.get(0);
        List<XWPFTableRow> rows = table0.getRows();
        XWPFTableCell imgCell = rows.get(0).getCell(0);
        List<XWPFParagraph> paragraphs = imgCell.getParagraphs();
        XWPFParagraph newPara = paragraphs.get(0);
        XWPFRun imageCellRunn = newPara.createRun();
        imageCellRunn.addPicture(new FileInputStream(headerMap.get("url")),getPictureType(headerMap.get("type")),headerMap.get("filename"), Units.toEMU(100), Units.toEMU(120));

//        imageCellRunn.addBreak();

//        while (iterator.hasNext()){
//            XWPFTableRow next = iterator.next();
//            Iterator<XWPFTableCell> cellIterator = next.getTableCells().iterator();
//            while (cellIterator.hasNext()){
//                XWPFTableCell xwpfTableCell = cellIterator.next();
//                if(xwpfTableCell.getText().contains("img")){
//
//                }
//            }
//        }

        //替换图片
//        int copyRow = 16;
//        int newRow = 17;
//        for (int i = 1; i<= 2;i++){
//            insertRow(xwpfTable, copyRow, newRow);
//            newRow++;
//        }

        //输出流到文件
        File file = new File(writeFile);
        FileOutputStream fos = new FileOutputStream(file);
        document.write(fos);
        if (inputStream != null) {
            inputStream.close();
        }
        if (fos != null) {
            fos.close();
        }
    }

    /**
     * 替换全部文本
     *
     * textMap
     * @param paragraphs 段落
     */
    public static void replaceAllTexts(Map<String, String> textMap, List<XWPFParagraph> paragraphs) {
        Set<Map.Entry<String, String>> textEntrySet = textMap.entrySet();
        for (Map.Entry<String, String> entry : textEntrySet) {
            String key = entry.getKey();
            String value = entry.getValue();
            // 替换全部文本
            replaceAllTexts(key, value, paragraphs);
        }
    }

    public static void replaceAllTexts(String key, String value, List<XWPFParagraph> paragraphs) {
        for (XWPFParagraph paragraph : paragraphs) {
            // 待替换文本
            String text = paragraph.getText();
            if (StringUtil.isNotEmpty(text) && text.indexOf(key) != -1) {
                List<XWPFRun> runs = paragraph.getRuns();
                // 只保留第一个Run
                for (int i = (runs.size() - 1); i > 0; i--) {
                    paragraph.removeRun(i);
                }
                runs.get(0).setText(text.replace(key, value), 0);
            }
        }
    }
    /**
     * 根据图片类型，取得对应的图片类型代码
     * @param picType
     * @return int
     */
    private static int getPictureType(String picType){
        int res = XWPFDocument.PICTURE_TYPE_PICT;
        if(picType != null){
            if(picType.equalsIgnoreCase("png")){
                res = XWPFDocument.PICTURE_TYPE_PNG;
            }else if(picType.equalsIgnoreCase("dib")){
                res = XWPFDocument.PICTURE_TYPE_DIB;
            }else if(picType.equalsIgnoreCase("emf")){
                res = XWPFDocument.PICTURE_TYPE_EMF;
            }else if(picType.equalsIgnoreCase("jpg") || picType.equalsIgnoreCase("jpeg")){
                res = XWPFDocument.PICTURE_TYPE_JPEG;
            }else if(picType.equalsIgnoreCase("wmf")){
                res = XWPFDocument.PICTURE_TYPE_WMF;
            }
        }
        return res;
    }



    /**
     * insertRow 在word表格中指定位置插入一行，并将某一行的样式复制到新增行
     * @param copyrowIndex 需要复制的行位置
     * @param newrowIndex 需要新增一行的位置
     * */
    public static void insertRow(XWPFTable table, int copyrowIndex, int newrowIndex) {
        // 在表格中指定的位置新增一行
        XWPFTableRow targetRow = table.insertNewTableRow(newrowIndex);
        // 获取需要复制行对象
        XWPFTableRow copyRow = table.getRow(copyrowIndex);
        //复制行对象
        targetRow.getCtRow().setTrPr(copyRow.getCtRow().getTrPr());
        //或许需要复制的行的列
        List<XWPFTableCell> copyCells = copyRow.getTableCells();
        //复制列对象
        XWPFTableCell targetCell = null;
        for (int i = 0; i < copyCells.size(); i++) {
            XWPFTableCell copyCell = copyCells.get(i);
            targetCell = targetRow.addNewTableCell();
            targetCell.getCTTc().setTcPr(copyCell.getCTTc().getTcPr());

            if (copyCell.getParagraphs() != null && copyCell.getParagraphs().size() > 0) {
                targetCell.getParagraphs().get(0).getCTP().setPPr(copyCell.getParagraphs().get(0).getCTP().getPPr());
                if (copyCell.getParagraphs().get(0).getRuns() != null
                        && copyCell.getParagraphs().get(0).getRuns().size() > 0) {
                    XWPFRun cellR = targetCell.getParagraphs().get(0).createRun();
                    cellR.setBold(copyCell.getParagraphs().get(0).getRuns().get(0).isBold());
                }
            }
            List<XWPFParagraph> paragraphs = targetCell.getParagraphs();
            for (int num = 1; num <= 2;num++ ){
                for (XWPFParagraph paragraph : paragraphs) {
                    List<XWPFRun> runs = paragraph.getRuns();
                    for (XWPFRun run : runs) {
                        run.setText("${}"+num, 0);
                    }
                }
            }

        }

    }
}
