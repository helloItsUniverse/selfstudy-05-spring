package com.ohgiraffers.common;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {

    /* 설명. final 을 붙이기 위해서 쓰는게 생성자 주입. */
    private final List<Product> items;

    public ShoppingCart() {
        items = new ArrayList<>();
    }

    /* 설명. 카트에 물품 담는 기능 */
    public void addItem(Product item) {
        items.add(item);
    }

    /* 설명. 카트의 물품 꺼내는 기능 */
    public List<Product> getItems() {
        return items;
    }
}
