package com.ohgiraffers.section01.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.List;

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
        System.out.println("logPointcut 동작");
    }

    /* 설명. 1. Before Advice */
    /* 설명.
     *  매개변수로 쓰인 JoinPoint
     *  : pointcut 으로 패치된 join point
     * */
    @Before("LoggingAspect.logPointcut()")
    public void logBefore(JoinPoint joinPoint) {
//        System.out.println("Before Advice 동작");
        System.out.println("Before joinPoint.getTarget(): " + joinPoint.getTarget());
        System.out.println("Before joinPoint.getSignature(): " + joinPoint.getSignature());
        if (joinPoint.getArgs().length > 0) {       // 타켓 메소드의 변수가 하나 이상이면
            System.out.println("Before joinPoint.getArgs()[0]: " + joinPoint.getArgs()[0]);
        }
    }

    /* 설명. 2. After Advice */
    @After("LoggingAspect.logPointcut()")
    public void logAfter(JoinPoint joinPoint) {
//        System.out.println("After Advice 동작");
        System.out.println("After joinPoint.getTarget(): " + joinPoint.getTarget());
        System.out.println("After joinPoint.getSignature(): " + joinPoint.getSignature());
        if (joinPoint.getArgs().length > 0) {       // 타켓 메소드의 변수가 하나 이상이면
            System.out.println("After joinPoint.getArgs()[0]: " + joinPoint.getArgs()[0]);
        }
    }

    /* 설명. 3. AfterReturning Advice */
    /* 설명. Pointcut 에 해당하는 위치가 담긴 메소드가 같은 클래스에 있으면 클래스 소속을 안 적어도 된다. */
    /* 설명. returning 에 쓰인 이름이 반환값이 넘어오는 매개변수명과 일치해야 한다.(result) */
    @AfterReturning(pointcut="logPointcut()", returning="result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        System.out.println("After Returning result: " + result);

        /* 설명. AfterReturning Advide 를 활용한 반환 값 가공도 가능하다. */
        if (result != null && result instanceof List) {
            ((List<MemberDTO>) result).add(new MemberDTO(3L, "반환 값 가공"));
        }
    }

    /* 설명. 4. AfterThrowing Advice */
    /* 설명. Throwing 속성 값과 매개변수명이 일치해야 한다.(exception) */
    @AfterThrowing(pointcut = "logPointcut()", throwing="exception")
    public void logAfterThrowing(Throwable exception) {
        System.out.println("After Throwing exception: " + exception);
    }

    /* 설명. 5. Around Advice */
    /* 설명.
     *  1. 이 advice 는 joinPoint 를 완전히 장악하기 때문에 앞서 살펴 본 advice 모두 Around advice 로 조립할 수 있다.
     *  2. 심지어 원본 joinPoint 를 언제 실행할지, 실행 자체를 안할지, 계속 실행할지 여부까지도 제어한다.
     *  3. Around advice 의 joinPoint 매개변수는 ProceedingJoinPoint 로 고정되어 있다.
     *  4. JoinPoint 의 하위 인터페이스로 원본 JoinPoint 의 진행 시점을 제어할 수 있다.
     *  5. JoinPoint 를 진행하는 호출을 잊는 경우가 자주 발생하기 때문에 주의해야 하며
     *     가능한 최소한의 요건을 충족하면서도 가장 기능이 약한 어드바이스를 쓰는게 바람직하다.
    * */
    @Around("logPointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("Around Before: " + joinPoint.getSignature().getName());
        Object result = joinPoint.proceed();    // 이 시점에 타겟 메소드 동작
        System.out.println("Around After: " + joinPoint.getSignature().getName());

        /* 설명. 실행된 타겟 메소드 반환(다른 어드바이스가 다시 실행할 수 있도록 반환한다. */
        return result;
    }
}
