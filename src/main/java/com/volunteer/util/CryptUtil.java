package com.volunteer.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class CryptUtil {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    public String encode(String rawPassword){
        return bCryptPasswordEncoder.encode(rawPassword);
    }

    public Boolean matches(String rawPassword,String encodedPassword){
        return bCryptPasswordEncoder.matches(rawPassword,encodedPassword);
    }
}
