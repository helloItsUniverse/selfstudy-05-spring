package com.ohgiraffers.section01.javaconfig;

import com.ohgiraffers.common.MemberDTO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ContextConfiguration.class);

        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println("beanName: " + beanName);
        }

        /* 설명. 1. bean의 ID를 이용해서 bean을 가져오는 방법 */
        MemberDTO member1 = (MemberDTO) context.getBean("member");
        System.out.println("member1 = " + member1);

        /* 설명. 2. bean의 클래스 메타 정보(bean의 타입)를 전달하여 가져오는 방법 */
        MemberDTO member2 = context.getBean(MemberDTO.class);
        System.out.println("member2 = " + member2);

        /* 설명. 3. bean의 ID와 클래스 메타 정보를 전달하여 가져오는 방법 */
        MemberDTO member3 = context.getBean("member", MemberDTO.class);
        System.out.println("member3 = " + member3);
    }
}
