package util;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * Util class for building queries.
 * @param <T> class that extends ItemEntity
 */
public class QueryBuilder<T extends ItemEntity> {

    /**
      * The column name in the database which want to search in.
      */
    private String column;

    /**
      * The operator (lower, equal, bigger) for numbers in the search.
      */
    private String operator;

    /**
      * The filter text which we want to find in the database.
      */
    private String text;

    /**
     * List of queries for concatenate multiply queries.
     */
    private List<QueryBuilder<T>> queries;

    /**
     * The class type of the item.
     */
    private Class<T> itemType;

    /**
     * Constructor of the QueryBuilder class.
     * @param itemType the class type of the item
     */
    public QueryBuilder(Class<T> itemType) {
        this.itemType = itemType;
        queries = new ArrayList<>();
    }

    /**
     * Sets the column field of the object.
     * @param column the column name of the search
     * @return the actual object
     */
    public QueryBuilder<T> withColumn(String column) {
        this.column = column;
        return this;
    }

    /**
     * Sets the operator field of the object.
     * @param operator the operator of the search
     * @return the actual object
     */
    public QueryBuilder<T> withOperator(String operator) {
        this.operator = operator;
        return this;
    }

    /**
     * Sets the text field of the object.
     * @param text the filter of the search
     * @return the actual object
     */
    public QueryBuilder<T> withText(String text) {
        this.text = text;
        return this;
    }

    /**
     * Sets the queries field of the object.
     * @param queries the another queries to concat
     * @return the actual object
     */
    public QueryBuilder<T> andQueries(List<QueryBuilder<T>> queries) {
        this.queries.addAll(queries);
        return this;
    }

    /**
     * Sets the queries field of the object.
     * @param queries the another queries to concat
     * @return the actual object
     */
    public QueryBuilder<T> andQueries(QueryBuilder<T>... queries) {
        this.queries.addAll(Arrays.asList(queries));
        return this;
    }

    /**
     * Builds the where condition of the search.
     * @return the where condition as string
     */
    private String buildWhereString() {
        if (column == null || text == null) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(" s." + column);

        switch (column) {
            case "name":
                sb.append(" LIKE '%" + text + "%'");
                break;
            case "type":
                sb.append(" = '" + text + "'");
                break;
            default:
                sb.append(" " + ((operator == null) ? "=" : operator) + " '" + text + "'");
                break;
        }

        return sb.toString();

    }

    /**
     * Builds the query string.
     * @return the query string
     */
    private String buildQueryString() {

        StringBuilder sb = new StringBuilder();
        sb.append("SELECT s FROM " +  itemType.getSimpleName() +  " s");

        if (column == null || text == null) {
            if (queries.isEmpty()) {
                return sb.toString();
            } else {
                return queries.get(0).andQueries(queries.subList(1, queries.size())).buildQueryString();
            }
        }
        sb.append(" WHERE " + buildWhereString());

        for (QueryBuilder<T> query : queries) {
            String whereClause = query.buildWhereString();
            if (whereClause != null) {
                sb.append(" AND " + whereClause);
            }
        }
        return sb.toString();
    }

    /**
     * Builds the search.
     * @return the TypedQuery
     */
    public TypedQuery<T> build() {
        return DBConnector.getEntityManager().createQuery(buildQueryString(), itemType);
    }

}
