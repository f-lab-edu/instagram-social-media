package com.social.instagram;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/*
    @EnableAspectJAutoProxy
    AspectJ의 @Aspect를 handling 할 수 있도록 지원해준다. Spring AOP는 메서드 실행의 기능만 사용 할 수 있지만
    AspectJ는 메서드 호출, 메서드 실행, 생성자 호출등 다양한 기능을 제공해주고 있다. 또한 AspectJ는 컴파일 시점에서 Weaving을 하기 때문에
    spring AOP보다 비교적 더 좋은 성능을 낸다.

    @EnableJpaAuditing
    JPA Auditing 어노테이션을 활성화 하는 기능
*/
@SpringBootApplication
@EnableAspectJAutoProxy
@EnableJpaAuditing
@EnableCaching
public class InstagramSocialApplication {

    public static void main(String[] args) {
        SpringApplication.run(InstagramSocialApplication.class, args);
    }

}