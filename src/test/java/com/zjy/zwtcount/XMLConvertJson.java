package com.zjy.zwtcount;

/**
 * @Date 2020/5/29 13:30
 * @Created by zjy
 */
public class XMLConvertJson {

    public static void main(String[] args) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n" +
                "<S:Envelope xmlns:S=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "<S:Body>\n" +
                "<ns2:lhjybasqByShbResponse xmlns:ns2=\"http://jygl.jypx.gzsyjxxh.gov/\">\n" +
                "<return>&lt;?xml version=&quot;1.0&quot; encoding=&quot;UTF-8&quot; standalone=&quot;yes&quot; ?&gt; \n" +
                "&lt;result&gt;\n" +
                "  &lt;code&gt;-1&lt;/code&gt;\n" +
                "  &lt;Message&gt;受理机构信息有误，请联系所属区公共就业服务机构查询。申报信息中该人员的姓名(xm)和系统中该人的姓名不一致，请正确填写该人员姓名信息；如需修改个人基本信息的，请持劳动者身份证原件到各公共就业服务机构窗口办理信息变更手续。现联系（暂住）地址属于必填项，且长度不能超过150个字符（75个汉字）。每周工作时间（小时）属于必填项，且长度不能超过3个字符。每月收入情况属于必填项，且长度不能超过20个字符。手机号码属于必填项，且长度不能超过11个字符。就业前身份不能为空!就业形式（灵活就业）不能为空!就业地址不能为空!“文化程度”为“大学专科及以上”时毕业时间不能为空!文化程度代码不符合要求!&lt;/Message&gt;\n" +
                "&lt;/result&gt;</return>\n" +
                "</ns2:lhjybasqByShbResponse>\n" +
                "</S:Body>\n" +
                "</S:Envelope>";

        xml.replace("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>", "");
        xml.replace("\n", "");

        System.out.println(xml);

    }
}
