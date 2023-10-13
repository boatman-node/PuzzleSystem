package com.kanghaopeng.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.kanghaopeng.Impl.QuestionsImpl;
import com.kanghaopeng.entity.Questions;
import com.kanghaopeng.mapper.QuestionsMapper;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionsService extends ServiceImpl<QuestionsMapper, Questions> implements QuestionsImpl {
    @Override
    public List<Questions> SelectTypeQuestions(String questiontypes) {

        return baseMapper.SelectTypeQuestions(questiontypes,20);
    }
}
