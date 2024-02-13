package com.ohgiraffers.section03.annotationconfig.subsection01.java;

/* 설명.
 *  section03 에서는 ComponentScan과 관련하여 java 클래스와 xml 방식으로 사용하는 방법을 확인할 예정
 *  이 중에서도 java 클래스와 @ComponentScan 방식을 자주 사용하고 있다.
* */

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ContextConfiguration.class);

        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println("beanName = " + beanName);
        }
    }
}
