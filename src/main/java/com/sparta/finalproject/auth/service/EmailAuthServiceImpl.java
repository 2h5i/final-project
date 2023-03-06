package com.sparta.finalproject.auth.service;

import java.util.Random;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailAuthServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;
    private int authNumber;     // 난수 발생

    public void makeRandomNumber() {
        // 난수의 범위 111111 ~ 999999 (6자리 난수)
        Random r = new Random();
        int checkNum = r.nextInt(888888) + 111111;
        System.out.println("인증번호 : " + checkNum);
        authNumber = checkNum;
    }


    //이메일 보낼 양식
    public String joinEmail(String email) {
        makeRandomNumber();
        String setFrom = "sulsa1544@gmail.com"; // email-config에 설정한 자신의 이메일 주소를 입력
        String toMail = email;
        String title = "회원 가입 인증 이메일 입니다."; // 이메일 제목
        String content =
            "GRASP 홈페이지를 방문해주셔서 감사합니다." +    //html 형식으로 작성 !
                "<br><br>" +
                "인증 번호는 " + authNumber + "입니다." +
                "<br><br>" +
                "회원가입 페이지로 돌아가 회원가입을 마치시기 바랍니다.";   // 이메일 내용
        mailSend(setFrom, toMail, title, content);

        return Integer.toString(authNumber);
    }

    //이메일 전송 메소드
    public void mailSend(String setFrom, String toMail, String title, String content) {
        MimeMessage mailMessage = mailSender.createMimeMessage();
        try {
            mailMessage.addRecipients(MimeMessage.RecipientType.TO, toMail);
            mailMessage.setSubject(title);
            mailMessage.setFrom(setFrom);
            mailMessage.setText(content, "utf-8", "html");
            mailSender.send(mailMessage);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String sendSimpleMessage(String to) throws Exception {
        return null;
    }
}
