package com.kanghaopeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kanghaopeng.Enum.Captcha;
import com.kanghaopeng.dtos.ResponseResult;
import com.kanghaopeng.entity.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersImpl extends IService<Users> {
    ResponseResult SignAccount(Users users,String CaptchaDate);

    ResponseResult<String> ModifyInformation(Users user,String CaptchaDate);

    ResponseResult  landingAccount(Users users);

}
