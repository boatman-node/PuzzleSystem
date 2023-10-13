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
    private Integer id;
    private String title;
    private String content;
    private String type;
    private String questiontypes;
    private Integer difficulty;
    private String answer;
}
