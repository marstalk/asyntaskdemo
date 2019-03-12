package com.marstalk.asyntaskdemo.async;

import java.util.List;

public interface SplitStrategy<T> {
    List<T> split(T t);

    T combine(List<T> list);
}
