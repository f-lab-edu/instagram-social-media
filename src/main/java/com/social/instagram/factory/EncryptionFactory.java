package com.social.instagram.factory;

import com.social.instagram.util.encoding.Sha256Util;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
    @Configuration
    스프링 IOC Container에게 해당 클래스를 Bean구성 Class임을 알려준다
*/
@Configuration
public class EncryptionFactory {

    /*
        @Bean
        스프링 컨테이너에 의해 관리되는 객체
    */
    @Bean
    public Sha256Util sha256Util() {
        return new Sha256Util();
    }

}