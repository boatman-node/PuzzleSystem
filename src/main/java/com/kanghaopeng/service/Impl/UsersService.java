package com.kanghaopeng.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kanghaopeng.Comment.CryptographicTools;
import com.kanghaopeng.Comment.EmailService;
import com.kanghaopeng.Comment.RedisService;
import com.kanghaopeng.Enum.AppHttpCodeEnum;
import com.kanghaopeng.Enum.Captcha;
import com.kanghaopeng.dtos.ResponseResult;
import com.kanghaopeng.entity.Users;
import com.kanghaopeng.mapper.UsersMapper;
import com.kanghaopeng.service.UsersImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static com.kanghaopeng.tools.SmallTools.generateCaptcha;

@Transactional
@Service
public class UsersService extends ServiceImpl<UsersMapper, Users> implements UsersImpl {

    @Autowired
    RedisService redisService;
    @Autowired
    EmailService emailService;
    @Autowired
    CryptographicTools tools;

    @Override
    public ResponseResult SignAccount(Users users, String CaptchaDate) {
        String s = users.getUsername() + "_" + Captcha.REGISTER;
        if (redisService.exists(s)&s==CaptchaDate) {
            if(getBaseMapper().OneUser(users.getUsername())==null){
                users.setPassword(tools.encrypt(users.getPassword()));
                getBaseMapper().insert(users);
                return ResponseResult.okResult(users);
            }
            return ResponseResult.errorResult(AppHttpCodeEnum.CAPTCHA_ERROR);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.DATA_EXIST);
    }

    @Override
    public ResponseResult<String> ModifyInformation(Users user, String CaptchaDate) {
        user.setPassword(tools.encrypt(user.getPassword()));
        String s = user + "_" + Captcha.AMEND;
        if (redisService.get(s)==CaptchaDate&
                getBaseMapper().update(user,new LambdaQueryWrapper<Users>().eq(Users::getUsername,user.getUsername()))==1) {
            redisService.remove(s);
            return ResponseResult.okResult("修改成功");
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.FAIL);
    }
    @Autowired
    Gson gson;
    @Override
    public ResponseResult landingAccount(Users users) {
        if(!users.getUsername().isBlank()&&!users.getPassword().isBlank()){
            Users users1 = baseMapper.OneUser(users.getUsername());
            if (tools.decrypt(users.getPassword(),users1.getPassword())) {
                HashMap<String, String> stringStringHashMap = new HashMap<>();
                Type type = new TypeToken<Map<String, Object>>(){}.getType();
                Map<String, Object> map = gson.fromJson(gson.toJson(users1), type);
                String s = tools.generateToken(map);
                stringStringHashMap.put("token",s);
                return ResponseResult.okResult(stringStringHashMap);
            }
            return ResponseResult.errorResult(AppHttpCodeEnum.LOGIN_PASSWORD_ERROR);
        }
        return ResponseResult.errorResult(AppHttpCodeEnum.FAIL);
    }

}
