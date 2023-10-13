package com.kanghaopeng.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kanghaopeng.Enum.AppHttpCodeEnum;
import com.kanghaopeng.Enum.Captcha;
import com.kanghaopeng.Impl.UsersImpl;
import com.kanghaopeng.dtos.ResponseResult;
import com.kanghaopeng.entity.Users;
import com.kanghaopeng.mapper.UsersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import static com.kanghaopeng.tools.SmallTools.generateCaptcha;

@Service
public class UsersService extends ServiceImpl<UsersMapper, Users> implements UsersImpl {
    @Autowired
    EncryptService encryptService;

    @Autowired
    RedisService redisService;

    @Autowired
    EmailService emailService;

    /*注册账户*/
    @Override
    public ResponseResult<String> SignAccount(Users users,String CaptchaDate){
        if(getBaseMapper().OneUser(users.getUsername())!=null){
        return ResponseResult.errorResult(AppHttpCodeEnum.EXIST_USERNAME);
        }
        users.setPassword(encryptService.encrypt(users.getPassword()));
        getBaseMapper().insert(users);
        String s = users.getUsername() + "_" + Captcha.REGISTER;
        if(!redisService.exists(s)){
            return ResponseResult.errorResult(AppHttpCodeEnum.FAIL,"验证码失效");
        }
        if (redisService.get(s)!=CaptchaDate) {
            return ResponseResult.errorResult(AppHttpCodeEnum.FAIL,"验证码错误");
        }
        return  ResponseResult.okResult("注册成功！");
    }
    /*发送验证码*/
    @Override
    public ResponseResult<String> verificationCode(Users users,Captcha captcha){
        String s = users.getUsername() + "_" + captcha;
        if (redisService.exists(s)) {
            return ResponseResult.errorResult(AppHttpCodeEnum.DUPLICATE_SUBMISSIONS,"请勿重复提交,请稍后再试！");
        }
        String generateCaptcha = generateCaptcha();
        redisService.set(s,generateCaptcha,3L);
        return emailService.SendMailbox(users.getEmail(),generateCaptcha);
    }

    /*修改数据*/
    @Override
    public ResponseResult<String> ModifyInformation(Users user,String CaptchaDate){
        user.setPassword(encryptService.encrypt(user.getPassword()));
        String s = user + "_" + Captcha.AMEND;
        if (redisService.get(s)==CaptchaDate&
                getBaseMapper().update(user,new LambdaQueryWrapper<Users>().eq(Users::getUsername,user.getUsername()))==1) {
            redisService.remove(s);
            return ResponseResult.okResult("修改成功");
        }
            return ResponseResult.errorResult(AppHttpCodeEnum.FAIL);
    }
}
