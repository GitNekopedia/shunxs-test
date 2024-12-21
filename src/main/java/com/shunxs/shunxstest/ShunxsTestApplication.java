package com.shunxs.shunxstest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.shunxs.shunxstest.mapper")
public class ShunxsTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShunxsTestApplication.class, args);
    }

}
