package com.kanghaopeng;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.kanghaopeng.Comment.CryptographicTools;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PuzzleSystemApplicationTests {
	@Autowired
	Gson gson;
	@Autowired
	CryptographicTools cryptographicTools;
    @Test
	void Test(){
		System.out.println(cryptographicTools.isOverdue("eyJhbGciOiJIUzI1NiJ9.eyJpZCI6My4wLCJ1c2VybmFtZSI6IjE5NjM0NzA3MTIiLCJwYXNzd29yZCI6IjM0NThmMGFkZWY5MDY3MmFkOGFmMzExMzAzMzBjZDU4IiwiZW1haWwiOiIxOTYzNDcwN0BxcS5jb20iLCJyb2xlIjoiTWFuYWdlIiwiZXhwIjoxNjk3NDc1MjE5fQ.sAeYYLPCGmcrVAm2Jbs8D3HpqFbEJavbqWItylcsvNM"));
	}
   /*1697386378*/
}
