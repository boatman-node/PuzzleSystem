package com.kanghaopeng;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.google.gson.Gson;
import com.kanghaopeng.Enum.Captcha;
import com.kanghaopeng.entity.Questions;
import com.kanghaopeng.entity.Records;
import com.kanghaopeng.entity.Users;
import com.kanghaopeng.mapper.QuestionsMapper;
import com.kanghaopeng.mapper.RecordsMapper;
import com.kanghaopeng.service.EmailService;
import com.kanghaopeng.service.RecordsService;
import com.kanghaopeng.service.RedisService;
import com.kanghaopeng.service.UsersService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;

@SpringBootTest
public class PuzzleSystemApplicationTests {



	@Autowired
	QuestionsMapper questionsMapper;

	@Autowired
	Gson gson;

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	RecordsService recordsService;
	@Autowired
	EmailService service;
	@Test
	public void Test00(){
		service.SendMailbox("1963470712@qq.com","ne");
	}
	@Test
	public void Test01(){
		questionsMapper.SelectTypeQuestions("数学").forEach(e-> System.out.println(gson.toJson(e)));
	}


	@Test
	public void Test02(){
		for (int i = 100; i < 200; i++) {
			Questions questions = new Questions();
			questions.setQuestiontypes("数学");
			questions.setTitle("数学高考题目");
			questions.setContent(i+"*"+i+"=?");
			questions.setType("填空题");
			questions.setAnswer(String.valueOf(i*i));
			questions.setDifficulty(3);
			questionsMapper.insert(questions);
		}
	}
	@Autowired
	UsersService usersService;
	@Test
	public void Test03(){
		Users users = new Users();
		users.setRole("Manage");
		users.setEmail("1963470712@qq.com");
		users.setPassword("13691943186");
		users.setUsername("15007074662");
		System.out.println(usersService.SignAccount(users, "eeeee").getErrorMessage());
	}

	@Autowired
	RedisService redisService;


	@Test
	public void Test04(){
		Users users = new Users();
		users.setEmail("1963470712@qq.com");
		users.setPassword("1369194318622");
		users.setUsername("15007074662");
		usersService.verificationCode(users,Captcha.AMEND);
		usersService.ModifyInformation(users,redisService.GetString(users.getUsername()+"_"+Captcha.AMEND));
	}

	@Test
	public void Test05(){
		ArrayList<Records> records = new ArrayList<>();
		for (int i = 1; i < 20; i++) {
			Records re= new Records();
			re.setUserId(1);
			re.setQuestionId(i);
			re.setIsCorrect(String.valueOf(i*i)==questionsMapper.selectOne(
					new LambdaQueryWrapper<Questions>().eq(Questions::getId,i)).getAnswer()?1:0);
			re.setAnswer(String.valueOf(i*i));
			records.add(re);
		}
		System.out.println(gson.toJson( recordsService.RecordsBatchInsert(records)));
	}
}
