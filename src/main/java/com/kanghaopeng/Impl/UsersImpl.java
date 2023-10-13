package com.kanghaopeng.Impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kanghaopeng.Enum.Captcha;
import com.kanghaopeng.dtos.ResponseResult;
import com.kanghaopeng.entity.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersImpl extends IService<Users> {
   ResponseResult<String> SignAccount(Users users,String CaptchaDate);
   ResponseResult<String> verificationCode(Users users, Captcha captcha);

   ResponseResult<String> ModifyInformation(Users user,String CaptchaDate);
}
