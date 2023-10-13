package com.kanghaopeng.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.kanghaopeng.dtos.ResponseResult;
import com.kanghaopeng.entity.Records;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

    @Select("<script>SELECT * FROM Records \n" +
            "        <if test=\"questionId != null and questionId != ''\">\n" +
            "    WHERE questionId = #{records.questionId}  \n" +
            "        </if>  \n" +
            "        <if test=\"isCorrect == 1 or isCorrect == 0\">\n" +
            "    AND isCorrect = #{records.isCorrect}  \n" +
            "        </if>  \n" +
            "        <if test=\"timestamp != null\">\n" +
            "    AND timestamp = #{records.timestamp}  \n" +
            "        </if> </script>")
    /**/
    List<Records> RecordSelectBatch(@Param("records") Records records);

}
