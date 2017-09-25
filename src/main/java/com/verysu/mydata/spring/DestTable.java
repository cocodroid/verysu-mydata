package com.verysu.mydata.spring;

/**
 * ${DESCRIPTION}
 *
 * @author Cocodroid
 * @create 2017-08-19 18:47
 */
public class DestTable {
    private String sql;
    /**
     * 主键字段名，默认是id
     */
    private String idField;
    /**
     * 主键生成策略，默认无
     */
    private String idStrategy;

    public String getSql() {
        return sql;
    }
    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getIdField() {
        return idField;
    }
    public void setIdField(String idField) {
        this.idField = idField;
    }

    public String getIdStrategy() {
        return idStrategy;
    }
    public void setIdStrategy(String idStrategy) {
        this.idStrategy = idStrategy;
    }

    @Override
    public String toString() {
        return "DestTable{" +
                "sql='" + sql + '\'' +
                ", idField='" + idField + '\'' +
                ", idStrategy='" + idStrategy + '\'' +
                '}';
    }
}
