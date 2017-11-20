package com.example.calculator;

/**
 * Created by JeongsikWoo on 2017. 11. 20..
 */

public interface StackADT <E> {
    boolean is_empty();

    boolean is_full();

    void push(E item);

    E pop();

    E peek();
}
