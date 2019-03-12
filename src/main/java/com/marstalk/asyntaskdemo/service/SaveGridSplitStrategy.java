package com.marstalk.asyntaskdemo.service;

import com.marstalk.asyntaskdemo.async.SplitStrategy;
import com.marstalk.asyntaskdemo.domain.Grid;
import com.marstalk.asyntaskdemo.domain.Row;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SaveGridSplitStrategy implements SplitStrategy<Grid> {

    @Override
    public List<Grid> split(Grid grid) {

        List<Grid> result = new ArrayList<>();
        int blockSize = 80;

        if (blockSize > grid.getRows().size()) {
            result.add(grid);
            return result;
        }else{
            for (int i = 0; i < grid.getRows().size(); i += blockSize) {
                List<Row> rows = new ArrayList<>();
                for (int j = 0; j < blockSize; j++) {
                    if ((i + j) < grid.getRows().size()) {
                        rows.add(grid.getRows().get(i + j));
                    }
                }
                Grid newGrid = new Grid();
                newGrid.setRows(rows);
                result.add(newGrid);
            }
        }


        return result;

    }

    @Override
    public Grid combine(List<Grid> list) {

        Grid resultGrid = new Grid();
        resultGrid.setRows(new ArrayList<>());
        for (Grid grid : list) {
            resultGrid.getRows().addAll(grid.getRows());
        }

        return resultGrid;
    }
}
