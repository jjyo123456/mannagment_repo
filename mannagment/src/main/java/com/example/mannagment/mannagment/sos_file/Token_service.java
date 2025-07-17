package com.example.mannagment.mannagment.sos_file;

import org.springframework.beans.factory.annotation.Autowired;

public class Token_service {
    @Autowired
    private TokenGenerator tokenGenerator;

    public String gennerate_token(String userid){
        String roomname = userid + "room";
        String token =  tokenGenerator.generateToken(userid , roomname);
        return token;
    }
}
