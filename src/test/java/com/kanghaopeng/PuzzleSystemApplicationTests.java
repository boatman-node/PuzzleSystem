package com.kanghaopeng;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import com.kanghaopeng.Enum.Captcha;
import com.kanghaopeng.dtos.ResponseResult;
import com.kanghaopeng.entity.Questions;
import com.kanghaopeng.entity.Records;
import com.kanghaopeng.entity.Users;
import com.kanghaopeng.mapper.QuestionsMapper;
import com.kanghaopeng.mapper.RecordsMapper;
import com.kanghaopeng.mapper.UsersMapper;
import com.kanghaopeng.service.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSender;

import java.util.ArrayList;
import java.util.List;

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

	@Autowired
	QuestionsService questionsService;
	@Test
	public void Test00(){
		service.SendMailbox("1963470712@qq.com","ne");
	}
	@Test
	public void Test01(){
		List<Questions> q1 = questionsMapper.SelectTypeQuestions("数学", 2);
		System.out.println(gson.toJson(q1));
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
		for (int i = 1; i < 100; i++) {
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
    @Autowired
	RecordsMapper mapper;
	@Autowired
	UsersMapper usersMapper;

	@Test
	public void Test06(){
		Records records = new Records();
		records.setUserId(1);
		records.setIsCorrect(1);
		System.out.println(records);
		ResponseResult<Records> responseResult = recordsService.RecordSelectBatch(records,5,1);
		System.out.println(gson.toJson(responseResult.getData()));
	}
}
