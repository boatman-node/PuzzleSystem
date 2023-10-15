package com.kanghaopeng.Comment;
import com.kanghaopeng.Enum.AppHttpCodeEnum;
import com.kanghaopeng.Enum.Captcha;
import com.kanghaopeng.dtos.ResponseResult;
import com.kanghaopeng.tools.SmallTools;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


@Service
public class EmailService {
    @Value("${Mailbox.Account}")
    private  String Account="boatman2023@163.com";
    @Value("${Mailbox.subject}")
    private String subject="BoatMan";
    @Autowired
    RedisService redisService;
    @Autowired
    JavaMailSender javaMailSender;
    public ResponseResult<String> SendMailbox(String toEmail,String Captcha,String UserName){
        String Small ;
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            mimeMessageHelper.setTo(toEmail);
            mimeMessageHelper.setFrom(toEmail);
            mimeMessageHelper.setSubject(subject);
            Small = SmallTools.generateCaptcha();
            mimeMessageHelper.setText(GetHtml(SmallTools.generateCaptcha()),true);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            return ResponseResult.errorResult(AppHttpCodeEnum.FAIL,"邮箱发送失败,请稍后尝试!");
        }
           redisService.set(UserName+"_"+Captcha,Small,3L);
        return ResponseResult.okResult(200,"邮箱发送成功!");
    }

    private String GetHtml(String Msg){
        return "<html>\n" +
                "<head>\n" +
                "    <style>\n" +
                "        .card {\n" +
                "            max-width: 500px;\n" +
                "            margin: auto;\n" +
                "            background-color: #f5f5f5;\n" +
                "            padding: 15px;\n" +
                "            border-radius: 5px;\n" +
                "            box-shadow: 0px 2px 5px 0px rgba(0,0,0,0.3);\n" +
                "            font-family: Arial, sans-serif;\n" +
                "        }\n" +
                "\n" +
                "        .card-title {\n" +
                "            font-size: 20px;\n" +
                "            color: #333;\n" +
                "            margin-bottom: 15px;\n" +
                "        }\n" +
                "\n" +
                "        .card-text {\n" +
                "            font-size: 16px;\n" +
                "            color: #666;\n" +
                "        }\n" +
                "\n" +
                "        .card-btn {\n" +
                "            display: block;\n" +
                "            width: 100%;\n" +
                "            padding: 10px;\n" +
                "            font-size: 18px;\n" +
                "            color: #fff;\n" +
                "            background-color: #007BFF;\n" +
                "            border: none;\n" +
                "            border-radius: 5px;\n" +
                "            cursor: pointer;\n" +
                "        }\n" +
                "    </style>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "<div class=\"card\">\n" +
                "    <h1 class=\"card-title\">BoatMan答题</h1>\n" +
                "    <p class=\"card-text\">您的验证码是"+Msg+"，请不要把验证码泄漏给其他人，3分钟内有效，如非本人请勿操作。</p>\n" +
                "    <table  class=\"card-btn\">"+Msg+"</table>\n" +
                "</div>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
    }
}