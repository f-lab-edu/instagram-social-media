package com.social.instagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class InstagramSocialApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstagramSocialApplication.class, args);
    }

}