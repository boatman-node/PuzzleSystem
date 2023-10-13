package com.kanghaopeng.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("Users")
public class Users {
    @TableId
    private int id;

    private String username;
    private String password;
    private String email;
    private String role;
    private Date registration_date;


}
