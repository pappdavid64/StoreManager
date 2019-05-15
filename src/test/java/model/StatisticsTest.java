package model;

import javafx.collections.FXCollections;
import org.junit.*;
import org.junit.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;


public class StatisticsTest {
    private Statistics stats;

    @Before
    public void init()
    {
        stats = new Statistics(
                FXCollections.observableArrayList(
                        new SoldItems("Milk", 10, 150, "food", new Date()),
                        new SoldItems("Chocolate", 33, 20, "sweet", new Date()),
                        new SoldItems("Salt", 50, 45, "spice", new Date())
                )
        );
    }

    @Test
    public void testStatistics()
    {
        assertEquals(10+33+50, stats.getSumQuantity());
        assertEquals((10*150)+(33*20)+(50*45), stats.getIncome());
    }
}
