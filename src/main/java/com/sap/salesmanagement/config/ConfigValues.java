package com.sap.salesmanagement.config;

import org.springframework.beans.factory.annotation.Value;

public class ConfigValues {

    @Value("${emailsender.from}")
    private String emailSenderFrom;

    public String getEmailSenderFrom() {
        return emailSenderFrom;
    }
}
