package com.kanghaopeng.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kanghaopeng.Comment.CryptographicTools;
import com.kanghaopeng.Comment.RedisService;
import com.kanghaopeng.Enum.AppHttpCodeEnum;
import com.kanghaopeng.Enum.Captcha;
import com.kanghaopeng.dtos.ResponseResult;
import com.kanghaopeng.entity.Questions;
import com.kanghaopeng.entity.Records;
import com.kanghaopeng.entity.Users;
import com.kanghaopeng.mapper.QuestionsMapper;
import com.kanghaopeng.service.QuestionsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsService extends ServiceImpl<QuestionsMapper, Questions> implements QuestionsImpl {
    @Autowired
    RedisService redisService;
    @Autowired
    CryptographicTools cryptographicTools;
    @Override
    public ResponseResult SelectTypeQuestions(String token, String questiontypes, int Count) {
        if (cryptographicTools.isOverdue(token)) {
            return ResponseResult.okResult( baseMapper.SelectTypeQuestions(questiontypes, Count));
        }
            return ResponseResult.errorResult(AppHttpCodeEnum.DATA_NOT_EXIST);
    }


}
