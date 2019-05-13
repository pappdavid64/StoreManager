package util;

import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class QueryBuilder<T extends ItemEntity> {
    private String column;
    private String operator;
    private String text;
    private List<QueryBuilder<T>> queries;
    private Class<T> itemType;

    public QueryBuilder(Class<T> itemType)
    {
        this.itemType = itemType;
        queries = new ArrayList<>();
    }

    public QueryBuilder<T> withColumn(String column)
    {
        this.column = column;
        return this;
    }

    public QueryBuilder<T> withOperator(String operator)
    {
        this.operator = operator;
        return this;
    }

    public QueryBuilder<T> withText(String text)
    {
        this.text = text;
        return this;
    }

    public QueryBuilder<T> andQueries(List<QueryBuilder<T>> queries)
    {
        this.queries.addAll(queries);
        return this;
    }

    public QueryBuilder<T> andQueries(QueryBuilder<T>... queries)
    {
        this.queries.addAll(Arrays.asList(queries));
        return this;
    }

    private String buildWhereString()
    {
        if(column == null || text == null)
            return null;

        StringBuilder sb = new StringBuilder();
        sb.append(" s." + column);

        switch (column)
        {
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

    private String buildQueryString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT s FROM " +  itemType.getSimpleName() +  " s");

        if(column == null || text == null)
        {
            if(queries.isEmpty())
                return sb.toString();
            else
            {
                return queries.get(0).andQueries(queries.subList(1,queries.size())).buildQueryString();
            }
        }
        sb.append(" WHERE " + buildWhereString());

        for(QueryBuilder<T> query : queries)
        {
            String whereClause = query.buildWhereString();
            if(whereClause != null)
            {
                sb.append(" AND " + whereClause);
            }
        }
        return sb.toString();
    }

    public TypedQuery<T> build()
    {
        return DBConnector.getEntityManager().createQuery(buildQueryString(), itemType);
    }

}
