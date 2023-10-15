package com.kanghaopeng.Controller;

import com.google.gson.Gson;
import com.kanghaopeng.Enum.RoleName;
import com.kanghaopeng.dtos.ResponseResult;
import com.kanghaopeng.entity.Users;
import com.kanghaopeng.service.Impl.UsersService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UsersController {
    @Autowired
    UsersService usersService;
    @PostMapping("/enroll")
    public ResponseResult SignAccount(Users users,String Captcha){
        return usersService.SignAccount(users,Captcha);
    }

    @GetMapping("/ModifyInformation")
    public ResponseResult ModifyInformation(Users user, String CaptchaDate){
        return usersService.ModifyInformation(user,CaptchaDate);
    }

    @PostMapping("/landingAccount")
    public ResponseResult landingAccount(Users user){
        return usersService.landingAccount(user);
    }
}
