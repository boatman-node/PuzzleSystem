package com.kanghaopeng.service;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
@Service
public class EncryptService {

    /*加密*/
    public String encrypt(String plainText) {
        return DigestUtils.md5Hex(plainText);
    }
    /*解密*/
    public boolean decrypt(String plainText, String encryptedText) {
        return encryptedText.equals(DigestUtils.md5Hex(plainText));
    }
}
