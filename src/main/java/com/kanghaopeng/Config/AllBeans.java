package com.kanghaopeng.Config;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AllBeans {
    @Bean("Gson")
    public Gson Gson(){
        return new Gson();
    }

}
