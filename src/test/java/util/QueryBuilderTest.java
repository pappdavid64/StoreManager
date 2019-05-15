package util;

import model.Items;
import model.SoldItems;
import util.DBConnector;
import util.QueryBuilder;

import org.junit.*;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;



public class QueryBuilderTest {



    @Before
    public void initEntityManager()
    {
        DBConnector.initEntityManager();
    }

    @After
    public void closeEntityManager()
    {
        DBConnector.closeEntityManager();
    }


    @Test
    public void testQueryBuilder()
    {
        String query = "SELECT s FROM Items s WHERE s.name LIKE '%re%'";
        String query2 = "SELECT s FROM Items s WHERE s.type = 'Food'";
        String query3 = "SELECT s FROM Items s WHERE s.price >= 100";
        assertEquals(new QueryBuilder<Items>(Items.class).withColumn("name").withText("re").build().getResultList(),
                DBConnector.getEntityManager().createQuery(query, Items.class).getResultList());
        assertNotEquals(new QueryBuilder<Items>(Items.class).withColumn("name").withText("res").build().getResultList(),
                DBConnector.getEntityManager().createQuery(query, Items.class).getResultList());

        assertEquals(new QueryBuilder<Items>(Items.class).withColumn("type").withText("Food").build().getResultList(),
                DBConnector.getEntityManager().createQuery(query2, Items.class).getResultList());
        assertNotEquals(new QueryBuilder<Items>(Items.class).withColumn("type").withText("foo").build().getResultList(),
                DBConnector.getEntityManager().createQuery(query2, Items.class).getResultList());

        assertEquals(new QueryBuilder<Items>(Items.class).withColumn("price").withText("100").withOperator(">=").build().getResultList(),
                DBConnector.getEntityManager().createQuery(query3, Items.class).getResultList());
        assertNotEquals(new QueryBuilder<Items>(Items.class).withColumn("price").withText("100").withOperator("<").build().getResultList(),
                DBConnector.getEntityManager().createQuery(query3, Items.class).getResultList());

        String query4 = "SELECT s FROM SoldItems s WHERE s.quantity < 100";

        assertEquals(new QueryBuilder<SoldItems>(SoldItems.class).withColumn("quantity").withText("100").withOperator("<").build().getResultList(),
                DBConnector.getEntityManager().createQuery(query4, SoldItems.class).getResultList());
        assertNotEquals(new QueryBuilder<SoldItems>(SoldItems.class).withColumn("quantity").withText("100").withOperator(">").build().getResultList(),
                DBConnector.getEntityManager().createQuery(query4, SoldItems.class).getResultList());

        String query5 = "SELECT s FROM SoldItems s WHERE s.name LIKE '%bre%' AND 2019-05-15 < Sold_date";

        QueryBuilder<SoldItems> firstQuery = new QueryBuilder<SoldItems>(SoldItems.class).withColumn("name").withText("bre");
        QueryBuilder<SoldItems> secondQuery = new QueryBuilder<SoldItems>(SoldItems.class).withColumn("soldDate").withText("2019-05-15").withOperator("<");
        QueryBuilder<SoldItems> thirdQuery = new QueryBuilder<SoldItems>(SoldItems.class).withColumn("soldDate").withText("2019-05-12").withOperator(">");

        assertEquals(new QueryBuilder<SoldItems>(SoldItems.class).andQueries(firstQuery, secondQuery).build().getResultList(),
                DBConnector.getEntityManager().createQuery(query5, SoldItems.class).getResultList());
        assertNotEquals(new QueryBuilder<SoldItems>(SoldItems.class).andQueries(firstQuery, secondQuery, thirdQuery).build().getResultList(),
                DBConnector.getEntityManager().createQuery(query5, SoldItems.class).getResultList());

    }
}
