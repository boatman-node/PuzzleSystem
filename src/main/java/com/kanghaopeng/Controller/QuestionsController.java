package com.kanghaopeng.Controller;

import com.kanghaopeng.dtos.ResponseResult;
import com.kanghaopeng.service.Impl.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Questions")
public class QuestionsController {
     @Autowired
    QuestionsService questionsService;

     @PostMapping("/SelectTypeQuestions")
     public ResponseResult SelectTypeQuestions(String token,String questiontypes,int count){
         System.out.println(questiontypes+token+count);
         return questionsService.SelectTypeQuestions(token,questiontypes,count);
     }

    @GetMapping("/cot/{count}")
    public int Test(@PathVariable("count") int count){
       return count;
    }
}
