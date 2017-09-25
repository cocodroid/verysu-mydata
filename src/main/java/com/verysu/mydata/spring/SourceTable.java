package com.verysu.mydata.spring;

/**
 * ${DESCRIPTION}
 *
 * @author Cocodroid
 * @create 2017-08-19 18:47
 */
public class SourceTable {
    private String sql;
    private boolean delete;

    public String getSql() {
        return sql;
    }
    public void setSql(String sql) {
        this.sql = sql;
    }

    public boolean isDelete() {
        return delete;
    }
    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    @Override
    public String toString() {
        return "SourceTable{" +
                "sql='" + sql + '\'' +
                ", delete=" + delete +
                '}';
    }
}
