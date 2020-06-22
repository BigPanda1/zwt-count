package com.zjy.zwtcount;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.zjy.zwtcount.mapper")
public class ZwtCountApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZwtCountApplication.class, args);
    }

}
