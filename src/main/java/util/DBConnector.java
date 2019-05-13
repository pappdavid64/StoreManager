package util;


import model.Items;
import model.SoldItems;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.List;

public class DBConnector {
    private static EntityManagerFactory entityManagerFactory;
    private static EntityManager entityManager;

    public static EntityManager getEntityManager()
    {
        return entityManager;
    }

    public static void initEntityManager()
    {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-persistence-unit-1");
        entityManager = entityManagerFactory.createEntityManager();
    }

    public static void closeEntityManager()
    {
        entityManager.close();
        entityManagerFactory.close();
    }

    //@Transactional
    public static void createItem(ItemEntity item)
    {
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();
    }

    public static List<Items> search(String column, String operator, String text)
    {
        return new QueryBuilder(Items.class).withColumn(column).withOperator(operator).withText(text).build().getResultList();
    }

    public static List<Items> getAll()
    {
        return new QueryBuilder(Items.class).build().getResultList();
    }

    //@Transactional
    public static void modify(ItemEntity item)
    {
        entityManager.getTransaction().begin();
        entityManager.merge(item);
        entityManager.getTransaction().commit();
    }

    //@Transactional
    public static void delete(ItemEntity item)
    {
        entityManager.getTransaction().begin();
        entityManager.remove(item);
        entityManager.getTransaction().commit();
    }
}
