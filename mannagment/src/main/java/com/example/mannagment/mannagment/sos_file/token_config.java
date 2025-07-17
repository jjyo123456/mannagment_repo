package com.example.mannagment.mannagment.sos_file;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class token_config {
    @Value("twilio.ACCOUNTSID")
    public String AccountSid;

    @Value("twilio.apiKeySid")
    public String api_key_sid;

    @Value("twilio.API_KEY_SECRET")
    public String Api_key_secret;
}
