package com.zjy.zwtcount;

import org.apache.poi.POIXMLDocumentPart;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.*;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * @Date 2020/5/9 9:45
 * @Created by zjy
 */
public class dengjihejianyuan {
    public static void main(String[] args) throws Exception {
        FileInputStream inputStream = new FileInputStream("C:\\Users\\zjy23\\Desktop\\政务通\\互联互通\\模板文档\\广州市城乡居民社会医疗保险参保登记申请表.docx");
        XWPFDocument document = new XWPFDocument(inputStream);
        Map<String, String> map = new LinkedHashMap<>();
        map.put("${name}", "张纪元");
        map.put("${idcard}", "6105212000010xxxxxx");
        map.put("${type_1}", "☑");
        map.put("${type_2}", "☑");
        map.put("${type_3}", "□");
        map.put("${type_4}", "□");
        map.put("${type_5}", " ");
        //身份证
        String idCard = "61052120000xxxxxxx";
        for (int i = 1; i <= idCard.length(); i++) {
            map.put("${i" + i + "}", String.valueOf(idCard.charAt(i - 1)));
        }
        map.put("${s_code}", "6666666");
        map.put("${s_name}", "北京大学");
        map.put("${s_year}", "张xxx");
        map.put("${is_new}", "2000");
        map.put("${grandle}", "六年级");
        map.put("${class}", "三班");
        map.put("${s_no}", "12456789");
        map.put("${sex_1}", "☑");
        map.put("${sex_2}", "□");
        map.put("${mz}", "汉");
        map.put("${year}", "2000");
        map.put("${month}", "01");
        map.put("${day}", "05");
        map.put("${region}", "昌平区");
        map.put("${hkxz_1}", "□");
        map.put("${hkxz_2}", "☑");
        map.put("${hkszd}", "中国（国籍）北京省 北京市  昌平区  朝阳街道/镇 居委/村 12合作社");
        map.put("${address}", "北京市朝阳区昌平路12号");
        map.put("${contacts}", "张xx");
        map.put("${phone}", "183293xxxxxxx");
        map.put("${gr1}", "☑");
        map.put("${gr2}", "□");
        map.put("${gr3}", "□");
        map.put("${gr4}", "□");
        map.put("${gr5}", "□");
        map.put("${yb_2019}", "☑");
        map.put("${yb_2020}", "☑");
        map.put("${ybdy_1}", "☑");
        map.put("${ybdy_2}", "□");
        map.put("${hm}", "张xx");
        map.put("${bank}", "光大银行");
        map.put("${bankCode}", "0322795545678913");
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
        String fileName = "C:\\Users\\zjy23\\Desktop\\政务通\\互联互通\\tmp_file\\666666.docx";
        File file = new File(fileName);
        FileOutputStream fos = new FileOutputStream(file);
        document.write(fos);
        if (inputStream != null) {
            inputStream.close();
        }
        if (fos != null) {
            fos.close();
        }
    }

//    public static void main(String[] args) throws Exception {
//        FileInputStream inputStream = new FileInputStream("C:\\Users\\zjy23\\Desktop\\政务通\\互联互通\\模板文档\\广州市城乡居民社会医疗保险停保减员申请表.docx");
//        XWPFDocument document = new XWPFDocument(inputStream);
//        Map<String, String> map = new LinkedHashMap<>();
//        map.put("${id1}", "1");
//        map.put("${id2}", "");
//        map.put("${id3}", "");
//        map.put("${xm1}", "张纪元");
//        map.put("${xm2}", "");
//        map.put("${xm3}", "");
//        map.put("${personCode1}", "123");
//        map.put("${personCode2}", "");
//        map.put("${personCode3}", "");
//        map.put("${type1}", "1");
//        map.put("${type2}", "");
//        map.put("${type3}", "");
//        map.put("${idCard1}", "610521200001051491");
//        map.put("${idCard2}", "");
//        map.put("${idCard3}", "");
//        map.put("${year1}", "2020");
//        map.put("${year2}", "");
//        map.put("${year3}", "");
//        map.put("${code1}", "456");
//        map.put("${code2}", "");
//        map.put("${code3}", "");
//        map.put("${yy1}", "1");
//        map.put("${yy2}", "");
//        map.put("${yy3}", "");
//        map.put("${name}", "张纪元");
//        map.put("${phone}", "18329344212");
//        map.put("${yyyy}", "2020");
//        map.put("${MM}", "5");
//        map.put("${dd}", "11");
//        List<XWPFParagraph> paragraphs = document.getParagraphs();
//        for (XWPFParagraph paragraph : paragraphs) {
//            if (paragraph.getText() == null || paragraph.getText().equals("")) continue;
//            for (String key : map.keySet()) {
//                if (paragraph.getText().contains(key)) {
//                    replaceInParagraph(paragraph, key, map.get(key));
//                }
//            }
//        }
//        for (XWPFTable tab : document.getTables()) {
//            for (XWPFTableRow row : tab.getRows()) {
//                for (XWPFTableCell cell : row.getTableCells()) {
//                    for (XWPFParagraph p : cell.getParagraphs()) {
//                        for (XWPFRun r : p.getRuns()) {
//                            String text = r.getText(0);
//                            for (String key : map.keySet()) {
//                                if (text != null && text.contains(key)) {
//                                    r.setText(map.get(text), 0);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }
//        String fileName = "C:\\Users\\zjy23\\Desktop\\政务通\\互联互通\\tmp_file\\777777.docx";
//        File file = new File(fileName);
//        FileOutputStream fos = new FileOutputStream(file);
//        document.write(fos);
//        if (inputStream != null) {
//            inputStream.close();
//        }
//        if (fos != null) {
//            fos.close();
//        }
//    }
//        for (int i = 0; i < paragraphs.size(); i++) {
//            for(Map.Entry e : map.entrySet()){
//                if (paragraphs.get(i).getParagraphText().contains(e.getKey().toString())){
//                    List<XWPFRun> runs = paragraphs.get(i).getRuns();
//                    for (XWPFRun run : runs) {
//
//                    }
//                }
//            }
//        }
//
//        for (XWPFTable tab : document.getTables()) {
//            for (XWPFTableRow row : tab.getRows()) {
//                for (XWPFTableCell cell : row.getTableCells()) {
//                    for (XWPFParagraph p : cell.getParagraphs()) {
//                        for (XWPFRun r : p.getRuns()) {
//                            String text = r.getText(0);
//                            for (String key : map.keySet()) {
//                                if (text != null && text.contains(key)) {
//                                    r.setText(map.get(text), 0);
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//        }


    private static void replaceInParagraph(XWPFParagraph xwpfParagraph, String oldString, String newString) {
        Map<String, Integer> pos_map = findSubRunPosInParagraph(xwpfParagraph, oldString);
        if (pos_map != null) {
            System.out.println("start_pos:" + pos_map.get("start_pos"));
            System.out.println("end_pos:" + pos_map.get("end_pos"));

            List<XWPFRun> runs = xwpfParagraph.getRuns();
            XWPFRun modelRun = runs.get(pos_map.get("end_pos"));
            XWPFRun xwpfRun = xwpfParagraph.insertNewRun(pos_map.get("end_pos") + 1);
            xwpfRun.setText(newString);
            System.out.println("字体大小：" + modelRun.getFontSize());
            if (modelRun.getFontSize() != -1)
                xwpfRun.setFontSize(modelRun.getFontSize());//默认值是五号字体，但五号字体getFontSize()时，返回-1
            xwpfRun.setFontFamily(modelRun.getFontFamily());
            for (int i = pos_map.get("end_pos"); i >= pos_map.get("start_pos"); i--) {
                System.out.println("remove run pos in :" + i);
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
    public static Map<String, Integer> findSubRunPosInParagraph(XWPFParagraph xwpfParagraph, String substring) {

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


}

//                                    if (cell.getText() == null || !cell.getText().contains("\n")) {
//                                        for (XWPFParagraph p2 : cell.getParagraphs()) {
//                                            for (XWPFRun run : p2.getRuns()) {//XWPFRun对象定义具有一组公共属性的文本区域
//                                                if (run.getText(0) != null && run.getText(0).contains("\n")) {
//                                                    String[] lines = run.getText(0).split("\n");
//                                                    if (lines.length > 0) {
//                                                        run.setText(lines[0], 0);
//                                                        for (int i = 1; i < lines.length; i++) {
//                                                            run.addBreak();//中断
//                                                            run.setText(lines[i]);
//                                                        }
//                                                    }
//                                                }
//                                            }
//                                        }
//                                    }


//        File source =new File("C:\\Users\\zjy23\\Desktop\\政务通\\互联互通\\广州市城乡居民社会医疗保险参保登记申请表.doc");
//        File dest =new File("C:\\Users\\zjy23\\Desktop\\政务通\\互联互通\\tmp_file\\广州市城乡居民社会医疗保险参保登记申请表.doc");
//        copyFileUsingJava7Files(source,dest);

//        InputStream is = new FileInputStream("C:\\Users\\zjy23\\Desktop\\政务通\\互联互通\\广州市城乡居民社会医疗保险参保登记申请表.doc");
//        HWPFDocument doc = new HWPFDocument (is);
//
//        Map<String, Object> contentMap = new HashMap<>();
//        contentMap.put("□", "\uF0FE");
//        Range range = doc.getRange();
//        range.replaceText("□", "\uF0FE");


//    private static void copyFileUsingJava7Files(File source, File dest)
//            throws IOException {
//        Files.copy(source.toPath(), dest.toPath());
//    }


