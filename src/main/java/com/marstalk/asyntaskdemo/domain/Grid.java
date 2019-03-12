package com.marstalk.asyntaskdemo.domain;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Grid {

    private List<Row> rows;

    public static Grid instance(int count) {
        ArrayList<Row> list = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Row r = new Row(i);
            list.add(r);
        }
        Grid grid = new Grid();
        grid.setRows(list);
        return grid;
    }
}
