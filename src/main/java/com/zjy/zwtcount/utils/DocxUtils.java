package com.zjy.zwtcount.utils;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Date 2020/5/10 19:09
 * @Created by zjy
 * docx文档工具类
 */
@Slf4j
public class DocxUtils {
    /**
     * 该方法只针对docx文档有效
     * 替换表格内容
     *
     * @param resourcePath 模板文件的位置
     * @param targetPath   输出docx文档位置
     * @param map          替换docx内容的集合 key=docx文档的变量 value=替换后的值
     * @param hasText        word表格之外是否有需要替换的变量
     */
    public static void replaceVariableTable(String resourcePath, String targetPath, Map<String, String> map,Boolean hasText) {
        try {
            //读取文件
            log.info("读取文件的位置：{}，输出文件位置：{}，要替换的参数：{}", resourcePath, targetPath, JSONObject.toJSONString(map));
            FileInputStream inputStream = new FileInputStream(resourcePath);
            XWPFDocument document = new XWPFDocument(inputStream);
            if(hasText){ //word替换表格之外的text
                replaceAllTexts(map,document.getParagraphs());
//                replaceInParagraphText(document,map);
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
            //输出流到文件
            File file = new File(targetPath);
            FileOutputStream fos = new FileOutputStream(file);
            document.write(fos);
            if (inputStream != null) {
                inputStream.close();
            }
            if (fos != null) {
                fos.close();
            }
        } catch (Exception e) {
            log.error("文档替换变量失败：{}", e);
        }
    }

    /**
     * 该方法只针对docx文档有效
     * 替换docx 文本内容
     *
     */
    public static void replaceInParagraphText(XWPFDocument document, Map<String, String> map) {
        List<XWPFParagraph> paragraphs = document.getParagraphs();
        for (XWPFParagraph paragraph : paragraphs) {
            String text = paragraph.getText();
            if ( text == null || text.equals("")) continue;
            for (String key : map.keySet()) {
                if (text.contains(key)) {
                    replaceInParagraph(paragraph, key, map.get(key));
                }
            }
        }
    }

    private static void replaceInParagraph(XWPFParagraph xwpfParagraph, String oldString, String newString) {
        Map<String, Integer> pos_map = findSubRunPosInParagraph(xwpfParagraph, oldString);
        if (pos_map != null) {
            List<XWPFRun> runs = xwpfParagraph.getRuns();
            XWPFRun modelRun = runs.get(pos_map.get("end_pos"));
            XWPFRun xwpfRun = xwpfParagraph.insertNewRun(pos_map.get("end_pos") + 1);
            xwpfRun.setText(newString);
            if (modelRun.getFontSize() != -1)
                xwpfRun.setFontSize(modelRun.getFontSize());//默认值是五号字体，但五号字体getFontSize()时，返回-1
            xwpfRun.setFontFamily(modelRun.getFontFamily());
            for (int i = pos_map.get("end_pos"); i >= pos_map.get("start_pos"); i--) {
                xwpfParagraph.removeRun(i);
            }
        }
    }

    /**
     * 找到段落中子串的起始XWPFRun下标和终止XWPFRun的下标
     *
     * @param xwpfParagraph
     * @param substring
     * @return
     */
    private static Map<String, Integer> findSubRunPosInParagraph(XWPFParagraph xwpfParagraph, String substring) {

        List<XWPFRun> runs = xwpfParagraph.getRuns();
        int start_pos = 0;
        int end_pos = 0;
        String subtemp = "";
        for (int i = 0; i < runs.size(); i++) {
            subtemp = "";
            start_pos = i;
            for (int j = i; j < runs.size(); j++) {
                if (runs.get(j).getText(runs.get(j).getTextPosition()) == null) continue;
                subtemp += runs.get(j).getText(runs.get(j).getTextPosition());
                if (subtemp.equals(substring)) {
                    end_pos = j;
                    Map<String, Integer> map = new HashMap<>();
                    map.put("start_pos", start_pos);
                    map.put("end_pos", end_pos);
                    return map;
                }
            }
        }
        return null;
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
//    private void createParagraphs(XWPFParagraph xwpfParagraph, String[] paragraphs) {
//        if(xwpfParagraph!=null){
//            for (int i = 0; i < paragraphs.length; i++) {
//                XmlCursor cursor = xwpfParagraph.getCTP().newCursor();
//                XWPFParagraph newParagraph = document.insertNewParagraph(cursor);
//                newParagraph.setAlignment(xwpfParagraph.getAlignment());
//                newParagraph.getCTP().insertNewR(0).insertNewT(0).setStringValue(paragraphs[i]);
//                newParagraph.setNumID(xwpfParagraph.getNumID());
//            }
//            document.removeBodyElement(document.getPosOfParagraph(xwpfParagraph));
//        }
//    }


}
