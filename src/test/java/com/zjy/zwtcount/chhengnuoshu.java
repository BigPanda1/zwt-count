package com.zjy.zwtcount;

import com.zjy.zwtcount.utils.DocxUtils;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Date 2020/5/19 9:47
 * @Created by zjy
 */
public class chhengnuoshu {

    public static void main(String[] args) {
        String readFile = "C:\\Users\\zjy23\\Desktop\\政务通\\互联互通\\模板文档\\个人书面承诺书.docx";
        String writeFile = "C:\\Users\\zjy23\\Desktop\\政务通\\互联互通\\tmp_file\\11111111111111.docx";
        Map<String, String> map = new LinkedHashMap<>();
        map.put("${name}", "张纪元");
        map.put("${phone}", "张纪元");
        map.put("${type}", "张纪元");
        map.put("${idcard}", "张纪元");
        map.put("${creditCode}", "张纪元");

        map.put("${type_1}", "☑");
        map.put("${type_2}", "□");
        map.put("${type_3}", "□");
        map.put("${type_4}", "□");
        map.put("${type_5}", "□");
        map.put("${yyyy1}", "张纪元");
        map.put("${yyyy2}", "    ");
        map.put("${yyyy3}", "    ");
        map.put("${yyyy4}", "    ");
        map.put("${yyyy5}", "    ");
        map.put("${MM1}", "5");
        map.put("${MM2}", "  ");
        map.put("${MM3}", "  ");
        map.put("${MM4}", "  ");
        map.put("${MM5}", "  ");
        map.put("${join1}", "   ");
        map.put("${join2}", "   ");
        map.put("${join3}", "   ");
        map.put("${join4}", "   ");
        map.put("${join5}", "   ");
        map.put("${stop1}", "□");
        map.put("${stop2}", "□");
        map.put("${stop3}", "□");
        map.put("${tfz1}", "□");
        map.put("${tfz2}", "□");
        map.put("${tfz3}", "□");
        map.put("${xj1}", "□");
        map.put("${xj2}", "□");
        map.put("${yyyyMMdd}", "2020年5月19日");
        DocxUtils.replaceVariableTable(readFile,writeFile,map,false);
    }
}
