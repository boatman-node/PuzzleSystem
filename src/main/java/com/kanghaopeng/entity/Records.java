package com.kanghaopeng.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@TableName("Records")
public class Records {
    @TableId
    @TableField("id")
    private Integer id;
    @TableField("userId")
    private Integer userId;
    @TableField("questionId")
    private Integer questionId;
    @TableField("answer")
    private String answer;
    @TableField("isCorrect")
    private Integer isCorrect;
    @TableField("timestamp")
    private Date timestamp;
}
