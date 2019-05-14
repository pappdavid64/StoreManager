package util;


import model.Items;
import model.SoldItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.transaction.Transactional;
import java.util.List;

/**
 * The class which connects to the database.
 */
public class DBConnector {
    /**
     * The entity manager factory of the project.
     */
    private static EntityManagerFactory entityManagerFactory;

    /**
     * The entity manager of the project.
     */
    private static EntityManager entityManager;

    /**
     * The logger of the DBConnector class
     */
    private static Logger logger = LoggerFactory.getLogger("DBConnector logger");

    /**
     * Gets the entityManager.
     * @return the entityManager
     */
    public static EntityManager getEntityManager() {
        return entityManager;
    }

    /**
     * initialize the entityManagerFactory and the entityManager.
     */
    public static void initEntityManager() {
        entityManagerFactory = Persistence.createEntityManagerFactory("jpa-persistence-unit-1");
        entityManager = entityManagerFactory.createEntityManager();
        logger.info("Entity manager created");
    }

    /**
     * Close the entityManager and the entityManagerFactory.
     */
    public static void closeEntityManager() {
        entityManager.close();
        entityManagerFactory.close();
        logger.info("Entity manager closed");
    }

    /**
     * Upload item to the database.
     * @param item the item which want to upload
     */
    public static void createItem(ItemEntity item) {
        entityManager.getTransaction().begin();
        entityManager.persist(item);
        entityManager.getTransaction().commit();
        logger.info(item + " added to the database");
    }

    /**
     * Search in the database.
     * @param column the column name of the search
     * @param operator the operator of the search
     * @param text the filter of the search
     * @return a list with the found items
     */
    public static List<Items> search(String column, String operator, String text) {
        return new QueryBuilder(Items.class).withColumn(column).withOperator(operator).withText(text).build().getResultList();
    }

    /**
     * Find all item in the database.
     * @return a list with all items
     */
    public static List<Items> getAll() {
        return new QueryBuilder(Items.class).build().getResultList();
    }

    /**
     * Modifies an item in the database.
     * @param item the item which want to modify
     */
    public static void modify(ItemEntity item) {
        entityManager.getTransaction().begin();
        entityManager.merge(item);
        entityManager.getTransaction().commit();
        logger.info(item + " modified");
    }

    /**
     * Delete an item in the database.
     * @param item the item which want to delete from the database
     */
    public static void delete(ItemEntity item) {
        entityManager.getTransaction().begin();
        entityManager.remove(item);
        entityManager.getTransaction().commit();
        logger.info(item + " deleted");
    }
}
