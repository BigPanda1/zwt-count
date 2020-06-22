package com.zjy.zwtcount;

import org.jsoup.nodes.Document;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

@SpringBootTest
public class WordToPdfTest {

    @Test
    public void test() throws IOException {

        Properties properties = new Properties();
        properties.load(new FileInputStream(new File("src/main/resources/templates/text.txt")));
        System.out.println(properties.getProperty("name"));

    }
}
