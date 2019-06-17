package infra.service;

import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

public class MailConfig {

    private static  JavaMailSenderImpl javaMailSender = null;

    public static JavaMailSenderImpl getMailConfig() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();
        String host = System.getenv("MAIL_HOST");
        String username = System.getenv("MAIL_USERNAME");
        String password = System.getenv("MAIL_PASSWORD");
        javaMailSender.setHost(host);
        javaMailSender.setPort(587);
        javaMailSender.setUsername(username);
        javaMailSender.setPassword(password);
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
        javaMailSender.setJavaMailProperties(props);
        return javaMailSender;
    }
}
