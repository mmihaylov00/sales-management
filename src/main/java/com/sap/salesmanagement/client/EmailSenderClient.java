package com.sap.salesmanagement.client;

import com.sap.salesmanagement.config.ConfigValues;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class EmailSenderClient {
    private final JavaMailSender javaMailSender;
    private final SpringTemplateEngine templateEngine;
    private final Logger logger;
    private final ConfigValues configValues;

    @Autowired
    public EmailSenderClient(JavaMailSender javaMailSender, SpringTemplateEngine templateEngine, ConfigValues configValues) {
        this.javaMailSender = javaMailSender;
        this.templateEngine = templateEngine;
        this.configValues = configValues;
        this.logger = LoggerFactory.getLogger(getClass());
    }

    public void sendMail(String to, String subject, String template, Context context) {
        sendMail(to, subject, template, context, null);
    }

    public void sendMail(String to, String subject, String template, Context context, List<Resource> resources) {
        try {
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message,
                    MimeMessageHelper.MULTIPART_MODE_MIXED_RELATED,
                    StandardCharsets.UTF_8.name());

            helper.setFrom(configValues.getEmailSenderFrom());
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(templateEngine.process(template, context), true);
            //todo check if this is working?
            if (resources != null) {
                for (Resource resource : resources) {
                    context.setVariable(resource.getFilename(), resource.getFilename());
                    helper.addInline(resource.getFilename(), resource);
                }
            }

            javaMailSender.send(message);
        } catch (MessagingException e) {
            this.logger.error("Could not send email " + subject + " to " + to + "", e);
        }
    }
}
