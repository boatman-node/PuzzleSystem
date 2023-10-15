package com.kanghaopeng.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.sql.Date;
@AllArgsConstructor
@NoArgsConstructor
public class Token {
    private String token;
    private String expirationDate;
}
