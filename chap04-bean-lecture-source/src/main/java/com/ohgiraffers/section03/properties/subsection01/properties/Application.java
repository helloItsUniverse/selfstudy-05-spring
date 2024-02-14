package com.ohgiraffers.section03.properties.subsection01.properties;

import com.ohgiraffers.common.Beverage;
import com.ohgiraffers.common.Bread;
import com.ohgiraffers.common.Product;
import com.ohgiraffers.common.ShoppingCart;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {

        ApplicationContext context
                = new AnnotationConfigApplicationContext(ContextConfiguration.class);

        String[] beanNames = context.getBeanDefinitionNames();
        for (String beanName : beanNames) {
            System.out.println("beanName = " + beanName);
        }

        /* 설명. 붕어빵, 딸기우유, 지리산암반수 bean 객체를 반환받아 변수에 담는다. */
        Product carpBread = context.getBean("carpBread", Bread.class);
        Product milk = context.getBean("milk", Beverage.class);
        Product water = context.getBean("water", Beverage.class);

        /* 설명. 첫 번째 손님이 쇼핑카트를 꺼내 물건을 담는다. */
        ShoppingCart cart1 = context.getBean("cart", ShoppingCart.class);
        cart1.addItem(carpBread);
        cart1.addItem(milk);

        /* 설명. 첫 번째 손님의 쇼핑카트에 담긴 물품 확인 */
        System.out.println("cart1 에 담긴 물품: " + cart1.getItems());


        /* 설명. ================================================================== */
        /* 설명. 두 번째 손님이 쇼핑카트를 꺼내 물건을 담는다. */
        ShoppingCart cart2 = context.getBean("cart", ShoppingCart.class);
        cart2.addItem(water);

        /* 설명. 두 번째 손님의 쇼핑카트에 담긴 물품 확인 */
        System.out.println("cart2 에 담긴 물품: " + cart2.getItems());       // cart1 에 담긴게 여기에도 있다?!

        System.out.println(System.identityHashCode(cart1) == System.identityHashCode(cart2));       // true 가 나온다
        // true 가 나온다 -> 객체가 하나만 생성됨 -> singleton... -> 그렇다면 ShoppingCart 메소드에 @Scope 를 달아야 한다.
    }
}
