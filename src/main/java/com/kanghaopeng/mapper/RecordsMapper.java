package com.kanghaopeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kanghaopeng.entity.Records;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecordsMapper extends BaseMapper<Records> {
    @Insert("<script>" +
            "INSERT INTO Records (userId,questionId,answer,isCorrect) VALUES " +
            "<foreach collection='RecordsList' item='item' separator=','>" +
            "(#{item.userId}, #{item.questionId}, #{item.answer}, #{item.isCorrect})" +
            "</foreach>" +
            "</script>")
    int RecordsBatchInsert(@Param("RecordsList")List<Records>RecordsList);
}
