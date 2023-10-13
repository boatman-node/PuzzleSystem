package com.kanghaopeng.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@TableName("Questions")
public class Questions {
    @TableId
    private int id;
    private String title;
    private String content;
    private String type;
    private String questiontypes;
    private int difficulty;
    private String answer;
}
