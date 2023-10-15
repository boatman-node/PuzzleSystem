package com.kanghaopeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kanghaopeng.entity.Questions;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface QuestionsMapper extends BaseMapper<Questions> {
     @Select("SELECT * FROM questions WHERE questiontypes = #{questiontypes} ORDER BY RAND() LIMIT #{Count}")
     List<Questions> SelectTypeQuestions(@Param("questiontypes")String questiontypes,@Param("Count")int Count);
}
