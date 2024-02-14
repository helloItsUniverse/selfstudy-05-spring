package com.ohgiraffers.section01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    /* 설명.
     *  타겟 클래스의 메소드에서 advice를 적용할 수 있는 지점들을 joinpoint 라고 한다.
     *  pointcut 은 여러 joinpoint 들에 advice 를 적용할 곳을 지정한 것이다.
     *  해당 joinPoint 에서 advice 가 동작한다.
     *
     * 설명.
     *  <PointCut 표현식>
     *  execution([수식어] 리턴타입 [클래스이름].이름(파라미터))
     *  1. 수식어: public, private 등 수식어를 명시(생략 가능)
     *  2. 리턴 타입: 리턴 타입을 명시
     *  3. 클래스 이름(패키지명 포함) 및 메소드 이름: 클래스이름과 메소드 이름을 명시
     *  4. 파라미터(매개변수): 메소드의 파라미터를 명시
     *  5. " * ": 1개이면서 모든 값이 올 수 있음
     *  6. " .. ": 0개 이상의 모든 값이 올 수 있음
     *
     * 설명.
     *  ex 1)
     *   execution(public Integer com.ohgiraffers.section01.advice.*.*(*))
     *   => com.ohgiraffers.section01.advice 패키지에 속해 있는 바로 다음 하위 클래스에
     *      파라미터가 1개인 모든 메소드이자 접근 제어자가 public 이고 반환형이 Integer 인 경우
     *  .
     *  ex 2)
     *   execution(* com.ohgiraffers.section01.advice.annotation..stu*(..))
     *   => com.ohgiraffers.section01.advice 패키지 및 하위 패키지에 속해 있고
     *      이름이 stu 로 시작하는 파라미터가 0개 이상인 모든 메소드이며 접근제어자와 반환형은 상관 없음
    * */
    @Pointcut("execution(* com.ohgiraffers.section01.aop.*Service.*(..))")
    public void logPointcut() {

    }

    @Before("LoggingAspect.logPointcut()")
    public void logBefore(JoinPoint joinPoint) {
        System.out.println("Before Advice 동작");
    }
}
