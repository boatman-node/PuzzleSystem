package com.kanghaopeng.Comment;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.Map;


@Component
public class CryptographicTools {
    /*加密*/
    public String encrypt(String plainText) {
        return DigestUtils.md5Hex(plainText);
    }
    /*解密*/
    public boolean decrypt(String plainText, String encryptedText) {
        System.out.println(DigestUtils.md5Hex(plainText));
        System.out.println(encryptedText);
        return encryptedText.equals(DigestUtils.md5Hex(plainText));
    }

    @Value("${jwt.secret}")
    private String jwtSecret;

    public String generateToken(Map<String, Object> claims) {
        return  Jwts.builder().setClaims(claims)
                .setExpiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000))
                .signWith(SignatureAlgorithm.HS256, jwtSecret).compact();
    }

    public boolean isOverdue(String token){
        if (token == null || token.isEmpty()) {
            return false;
        }
        try {
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            long expirationTime = claims.getExpiration().getTime() / 1000; // 将毫秒转换为秒
            long currentTime = System.currentTimeMillis() / 1000; // 将毫秒转换为秒
            System.out.println(expirationTime-currentTime);
            if ((expirationTime-currentTime>=0)&&(expirationTime-currentTime<=3600)) {
                System.out.println("-------------------");
            }
            if (expirationTime < currentTime) {
                return false;
            }
        } catch (Exception e) {
            return false;
        }
         return true;
    }


    public Map<String, Object> decodeToken(String token) {
        return Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();
    }
}
