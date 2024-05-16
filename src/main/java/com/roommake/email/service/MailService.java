package com.roommake.email.service;

import com.roommake.user.exception.EmailException;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    // 발송 이메일 계정 관리자 계정으로 고정
    @Value("${spring.mail.username}")
    private String emailFrom;

    /**
     * 메일 발송을 지원한다.
     *
     * @param toEmail      email 받을 주소
     * @param subject      메일 제목
     * @param templateName email 폴더에 담아둔 html 파일 이름
     * @param content      메일 내용에 담길 콘텐츠
     * @throws EmailException 메일 전송 예외
     */
    @Async
    public void sendEmail(String toEmail, String subject, String templateName, Map<String, Object> content) throws EmailException {

        MimeMessage message = mailSender.createMimeMessage();

        // html 템플릿 경로 지정
        String resource = "email/" + templateName + ".html";

        // Map 객체에 담긴 것 context에 담기
        Context context = new Context();
        content.forEach((key, value) -> {
            context.setVariable(key, value);
        });

        // html 파일 경로와 context를 받아서 html 템플릿 반환
        String html = templateEngine.process(resource, context);
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true);

            helper.setFrom(emailFrom);
            helper.setTo(toEmail);
            helper.setSubject(subject);
            helper.setText(html, true);

            mailSender.send(message);
        } catch (MessagingException ex) {
            throw new EmailException("이메일 발송 중 오류 발생", ex);
        }
    }
}
