package com.kanghaopeng.entity;

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
    private int id;
    private int userId;
    private int questionId;

    private String answer;

    private int isCorrect;

    private Date timestamp;


}
