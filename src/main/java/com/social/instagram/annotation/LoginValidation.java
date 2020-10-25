package com.social.instagram.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
    @Target
    어노테이션의 타켓을 지정하고 ElementType은 Enum구성되어 있다. ElementType에 값이 없는 상수를
    설정하면 컴파일 에러 난다.

    @Retention
    어노테이션의 지속기간을 나타내며 RetentionPolicy.RUNTIME 속성은 JVM이 자바 바이트코드가 담긴
    class 파일에서 런타임환경을 구성하고 런타임을 종료할 때까지 메모리는 살아있다.
*/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginValidation {

}