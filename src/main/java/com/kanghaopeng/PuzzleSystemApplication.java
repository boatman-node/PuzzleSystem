package com.kanghaopeng;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class PuzzleSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(PuzzleSystemApplication.class, args);
    }

    @GetMapping("/User")
    public String test (){
        return " USER";
    }

}
