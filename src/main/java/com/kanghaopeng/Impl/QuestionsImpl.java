package com.kanghaopeng.Impl;

import com.baomidou.mybatisplus.extension.service.IService;
import com.kanghaopeng.entity.Questions;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionsImpl extends IService<Questions> {
    List<Questions> SelectTypeQuestions(String questiontypes);
}
