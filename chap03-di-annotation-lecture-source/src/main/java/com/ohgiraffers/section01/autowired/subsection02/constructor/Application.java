package com.ohgiraffers.section01.autowired.subsection02.constructor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {

        /* 설명.
         *  AnnotationConfigApplicationContext 생성자에 basePackages 문자열을 전달하여
         *  ComponentScan 개념을 따로 설정 클래스 없이 바로 적용할 수도 있다.
         * */

        ApplicationContext context
                = new AnnotationConfigApplicationContext("com.ohgiraffers.section01");

        /* 설명. subsection01 에 있는 BookService 타입의 bean 과의 혼선을 방지하고자 bean 이름(id) 수정 */
        BookService bookService = context.getBean("bookServiceConstructor", BookService.class);

        /* 설명. 전체 도서 목록 조회 후 출력 확인 */
        bookService.findAllBook().forEach(System.out::println);

        /* 설명. 도서 번호로 검색 후 출력 확인 */
        System.out.println(bookService.searchBookBySequence(1));
        System.out.println(bookService.searchBookBySequence(2));
    }
}
