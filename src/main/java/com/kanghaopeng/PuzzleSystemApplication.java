package com.kanghaopeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@MapperScan("com.kanghaopeng.mapper")
@SpringBootApplication
public class PuzzleSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PuzzleSystemApplication.class, args);
    }

}
