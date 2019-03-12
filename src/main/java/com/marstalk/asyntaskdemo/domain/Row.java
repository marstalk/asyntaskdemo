package com.marstalk.asyntaskdemo.domain;

import lombok.Data;

@Data
public class Row {

    private int id;

    public Row(int id) {
        this.id = id;
    }
}
