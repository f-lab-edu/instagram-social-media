package com.social.instagram.validation;

import org.springframework.validation.FieldError;

import java.util.List;

/*
    ErrorMessage 사용 이유
    1. 불변 객체를 만들기 위한 목적
    @Valid 유효성에 대한 에러메시지 만드는데 List<Field> 타입을 이용하여 데이터를 처리하고 반환 해준다.
    하지만 이때 List<Field>의 메소드에 대한 조작이 가능한데 이 말은 데이터를 조작할 수 있다는 말이다. 소프트웨어 규모가 커지고 있는 상황에서
    불변 객체는 아주 중요하다. 각각의 객체들이 절대 값이 바뀔 일이 없다는게 보장되면 그 만큼 코드를 이해하고 수정하는데 side-effect가 최소화 되기
    때문에 불변으로 만들었다.

    2. 의도하고자 하는 naming 하기 위한 목적
    List<Field>의 변수를 사용하는 것보다 의도가 담긴 클래스 naming을 사용한다면 가독성 뿐만 아니라 사용하고자 하는 목적을 쉽게 파악 할 수 있다.
*/
public class ErrorMessage {

    private final List<FieldError> errorMessage;

    public ErrorMessage(List<FieldError> errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        StringBuilder errorResult = new StringBuilder();

        for (FieldError fieldError : errorMessage) {
            errorResult.append(fieldError.getDefaultMessage()).append("\n");
        }

        return errorResult.toString();
    }

}