package com.kanghaopeng.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kanghaopeng.dtos.ResponseResult;
import com.kanghaopeng.entity.Questions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsImpl extends IService<Questions> {
    ResponseResult SelectTypeQuestions(String token,String questiontypes, int Count);
}
