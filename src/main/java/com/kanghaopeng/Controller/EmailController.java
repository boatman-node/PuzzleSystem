package com.kanghaopeng.Controller;

import com.google.gson.Gson;
import com.kanghaopeng.Comment.EmailService;
import com.kanghaopeng.Enum.Captcha;
import com.kanghaopeng.dtos.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/email")
public class EmailController {
    @Autowired
    EmailService emailService;
    @Autowired
    Gson gson;
    @PostMapping("/sendEmail")
    public ResponseResult GetEmail(@RequestBody Map<String,String> data){
        System.out.println(gson.toJson(data));
       return   emailService.SendMailbox(data.get("Email"),data.get("Type"),data.get("UserName"));
    }

}
