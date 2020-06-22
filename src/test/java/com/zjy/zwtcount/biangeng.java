package com.zjy.zwtcount;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.zjy.zwtcount.utils.DocxUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.*;


/**
 * @Date 2020/5/9 9:45
 * @Created by zjy
 */

@Slf4j

public class biangeng {

    public static void main(String[] args) throws Exception {
        String resourcePath = "C:\\Users\\zjy23\\Desktop\\政务通\\互联互通\\模板文档\\广州市城乡居民社会医疗保险个人资料变更表.docx";
        String targetPath = "C:\\Users\\zjy23\\Desktop\\政务通\\互联互通\\tmp_file\\8888888.docx";
        Map<String, String> map = new LinkedHashMap<>();
        map.put("${unitname}", "越秀区梅花村街劳动和保障服\n");
        map.put("${unitcode}", "4402120121");
        map.put("${xm}", "张纪元");
        map.put("${owncode}", "610521200001051491");
        map.put("${oldxm}", "张纪中");
        map.put("${newxm}", "张纪元");
        map.put("${oldtype}", "1");
        map.put("${newtype}", "2");
        map.put("${oldidcard}", "6105212000xxxxx");
        map.put("${newidcard}", "6105210xxasdasdasd");
        map.put("${oldbirthday}", "1");
        map.put("${newbirthday}", "2");
        map.put("${oldhkszd}", "1");
        map.put("${newhkszd}", "2");
        map.put("${oldaddress}", "1");
        map.put("${newaddress}", "2");
        map.put("${oldlxr}", "1");
        map.put("${newlxr}", "2");
        map.put("${oldphone}", "1");
        map.put("${newphone}", "2");
        map.put("${oldgrsf}", "1");
        map.put("${newgrsf}", "2");
        map.put("${oldhm}", "1");
        map.put("${newhm}", "2");
        map.put("${oldbank}", "1");
        map.put("${newbank}", "2");
        map.put("${oldbankcode}", "1");
        map.put("${newbankcode}", "2");
        map.put("${oldcbdw}", "思域");
        map.put("${newcbdw}", "阿里巴巴");
        map.put("${olddnhb}", "1");
        map.put("${newdnhb}", "2");
        map.put("${oldmark}", "1");
        map.put("${newmark}", "2");
        map.put("${applyname}", "张纪元");
        map.put("${applyphone}", "18329344212");
        map.put("${yyyy}", "2020");
        map.put("${MM}", "05");
        map.put("${dd}", "11");
        FileInputStream inputStream = new FileInputStream(resourcePath);
        XWPFDocument document = new XWPFDocument(inputStream);
        Iterator<XWPFParagraph> itPara = document.getParagraphsIterator();
        while (itPara.hasNext()) {
            XWPFParagraph paragraph = itPara.next();
            List<XWPFRun> runs = paragraph.getRuns();
            for (int i = 0; i < runs.size(); i++) {
                String oneparaString = runs.get(i).getText(runs.get(i).getTextPosition());
                if (StringUtils.isBlank(oneparaString)){
                    continue;
                }
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    oneparaString = oneparaString.replace(entry.getKey(), entry.getValue());
                }
                if(runs.get(i).getText(runs.get(i).getTextPosition()).contains("unitname") && oneparaString.contains("\n") && oneparaString.length() > 16){
                    String[] lines = oneparaString.split("\n");
                    runs.get(i).setText(lines[0],0);
                    runs.get(i).addBreak();
                }else if(runs.get(i).getText(runs.get(i).getTextPosition()).contains("unitname") && oneparaString.contains("\n") && oneparaString.length() <= 16){
                    String[] lines = oneparaString.split("\n");
                    if(lines[0].length() != 16){
                        lines[0] = formatStr(lines[0],16);
                    }
                    runs.get(i).setText(lines[0],0);
                }else {
                    runs.get(i).setText(oneparaString, 0);
                }

            }
        }
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
        FileOutputStream outStream = null;
        outStream = new FileOutputStream(targetPath);
        document.write(outStream);
        outStream.close();

    }

    /**
     * 生成指定长度字符串，不足位右补空格
     * @param str
     * @param length
     * @return
     */
    private static String formatStr(String str, int length) {
        int strLen;
        if (str == null) {
            strLen = 0;
        }else{
            strLen= str.length();
        }

        if (strLen == length) {
            return str;
        } else if (strLen < length) {
            int temp = length - strLen;
            String tem = "";
            for (int i = 0; i < temp; i++) {
                tem = tem + " ";
            }
            return str + tem;
        }else{
            return str.substring(0,length);
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
}


