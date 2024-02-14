package com.ohgiraffers.section02.initdestroy;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class ContextConfiguration {
    @Bean
    public Product carpBread() {
        return new Bread("붕어빵", 1000, new java.util.Date());
    }

    @Bean
    public Product milk() {
        return new Beverage("딸기우유", 1500, 500);
    }

    @Bean
    public Product water() {
        return new Beverage("지리산암반수", 3000, 500);
    }

    @Bean
//    @Scope("singleton")     // 스프링 컨테이너(IoC Container)는 bean 객체를 기본적으로 singleton 으로 관리한다.
    @Scope("prototype")     // 같은 타입의 bean 을 꺼낼 때마다 새로운 객체를 만들 수 있게끔 한다.
    // bean scope 를 수정하게 되면 bean 객체의 life cycle 을 다른 주기로 가져갈 수 있다.
    public ShoppingCart cart() {
        return new ShoppingCart();
    }

    /* 설명. bean 의 생성 시점과 소멸 시점에 실행할 메소드 이름을 설정할 수 있다. */
    @Bean(initMethod = "openShop", destroyMethod = "closeShop")
    public Owner owner() {
        return new Owner();
    }

}
