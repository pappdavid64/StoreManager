package model;

import javafx.collections.ObservableList;

/**
 * Class for get statistics from the searches in the SearchMenu
 */
public class Statistics {
    /**
     * The summary of quantity of the sold items in the found items
     */
    private int sumQuantity;

    /**
     * The income from the found items
     */
    private int income;

    /**
     * The constructor of the class
     * @param soldItems the sold items from the search
     */
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

    /**
     * Gets the sumQuantity
     * @return the sumQuantity
     */
    public int getSumQuantity()
    {
        return sumQuantity;
    }

    /**
     * Gets the income
     * @return the income
     */
    public int getIncome()
    {
        return income;
    }
}
