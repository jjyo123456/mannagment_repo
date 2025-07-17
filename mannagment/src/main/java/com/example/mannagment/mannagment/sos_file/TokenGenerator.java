package com.example.mannagment.mannagment.sos_file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.twilio.jwt.accesstoken.AccessToken;
import com.twilio.jwt.accesstoken.VideoGrant;

@Component
public class TokenGenerator {


    @Autowired
    private token_config config;

    public String generateToken(String identity, String roomName) {
        VideoGrant grant = new VideoGrant().setRoom(roomName);

        AccessToken token = new AccessToken.Builder(
                config.AccountSid,
                config.api_key_sid,
                config.Api_key_secret
        ).identity(identity).grant(grant).ttl(3600).build();

        return token.toJwt();
    }
}
