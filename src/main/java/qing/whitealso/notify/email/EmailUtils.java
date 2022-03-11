package qing.whitealso.notify.email;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

/**
 * @author baiye
 * @since 2021/7/19 12:27 下午
 **/
public class EmailUtils {

    private static final Logger log = LoggerFactory.getLogger(EmailUtils.class);

    //邮件服务器地址
    private static final String mailServer = "smtp.office365.com";

    //用户名
    private final static String from = "jiangliuer_shawn@outlook.com";

    //密码
    private final static String pass = "***********";

    public static boolean sendMail(String receive, String subject, String msg, String messageType) {
        if (StringUtils.isBlank(messageType)) {
            messageType = "text/html;charset=gb2312";
        }
        if (StringUtils.isEmpty(receive)) {
            return false;
        }
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", mailServer);
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.socketFactory.port", "587");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        try {
            Session session = Session.getDefaultInstance(properties, new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(from, pass);
                }
            });
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            String[] receives = receive.split(",");
            Address[] address = new InternetAddress[receives.length];
            for (int i = 0; i < receives.length; i++) {
                address[i] = new InternetAddress(receives[i]);
            }
            message.addRecipients(Message.RecipientType.TO, address);
            message.setSubject(subject);
            message.setContent(msg, messageType);
            Transport.send(message);
            return true;
        } catch (Exception e) {
            log.error("【发送邮件异常】e -> ", e);
        }
        return false;
    }
}
