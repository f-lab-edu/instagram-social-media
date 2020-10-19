package com.social.instagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/*
    @EnableAspectJAutoProxy
    @Aspect를 제공해주고 스프링 XML 설정의 <aop:aspectj-autoproxy/>을 대신해서 자바에 설정한다.
*/
@SpringBootApplication
@EnableAspectJAutoProxy
public class InstagramSocialApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstagramSocialApplication.class, args);
    }

}