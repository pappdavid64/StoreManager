package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Statistics {
    private int sumQuantity;
    private int income;

    public Statistics(ObservableList<SoldItems> soldItems)
    {
        sumQuantity = 0;
        income = 0;
        for(SoldItems item : soldItems)
        {
            sumQuantity += item.getQuantity();
            income += item.getPrice()*item.getQuantity();
        }
    }

    public int getSumQuantity()
    {
        return sumQuantity;
    }

    public int getIncome()
    {
        return income;
    }
}
