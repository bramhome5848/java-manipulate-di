package kr.com.lkj.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) //확장 설정
@Target({ElementType.TYPE, ElementType.FIELD})   //사용할 수 있는 위치
@Inherited  //상속이 가능한 annotation 설정
public @interface MyAnnotation {

    //default 주지 않을시 기본 값을 주지 않으면 compile error
    String name() default "lkj";

    int number() default 100;
}
