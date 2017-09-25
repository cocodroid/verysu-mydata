package com.verysu.mydata.util;

import com.alibaba.druid.sql.ast.SQLExpr;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.ast.expr.SQLIdentifierExpr;
import com.alibaba.druid.sql.ast.expr.SQLVariantRefExpr;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.google.common.base.Preconditions;
import com.verysu.mydata.core.DataException;
import com.verysu.mydata.id.IdStrategy;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ${DESCRIPTION}
 *
 * @author Cocodroid
 * @create 2017-08-23 12:58
 */
public class SqlUtil {
    /**
     *  idField字段有没有在SQL里
     * @param idField
     * @param sql
     * @return -1 不存在对应
     */
    public static int idInSqlIndex(final String idField ,final String sql){
        Preconditions.checkNotNull(idField);
        MySqlStatementParser parser = new MySqlStatementParser(sql);
        SQLStatement sqlStatement = parser.parseInsert();
        MySqlInsertStatement mySqlInsertStatement = (MySqlInsertStatement)sqlStatement;
        List<SQLExpr> columns = mySqlInsertStatement.getColumns();

        int index = -1;
        for (int i=0; i<columns.size();i++) {
            index = i+1;
            SQLIdentifierExpr sqlIdentifierExpr = (SQLIdentifierExpr) columns.get(i);
            if (idField.equals(sqlIdentifierExpr.getName())) {
                break;
            }
        }
        return index;
    }

    /**
     * SQL占位符列对应的字段值
     * @param sql
     * @return
     * @throws DataException
     */
    public static Map columnMapValue(String sql) throws DataException {
        MySqlStatementParser  parser = new MySqlStatementParser(sql);
        SQLStatement sqlStatement = parser.parseInsert();
        MySqlInsertStatement mySqlInsertStatement = (MySqlInsertStatement)sqlStatement;
        List<SQLExpr> columns = mySqlInsertStatement.getColumns();
        List<SQLExpr> values = mySqlInsertStatement.getValues().getValues();
        if(columns.size() != values.size()){
            throw new DataException("The number of values should be equal to the number of columns!");
        }
        Map<String,String> map = new HashMap<>();
        for (int i = 0; i < columns.size(); i++) {
            SQLIdentifierExpr sqlIdentifierExprColumn = (SQLIdentifierExpr)columns.get(i);
            SQLExpr sqlExprV = values.get(i);
            if(sqlExprV instanceof SQLVariantRefExpr) {
                SQLVariantRefExpr sqlVariantRefExprValue = (SQLVariantRefExpr) values.get(i);
                map.put(sqlIdentifierExprColumn.getName(), sqlVariantRefExprValue.getName());
            }
        }
        return map;
    }

    /**
     * 反射获得ID生成策略
     * @param idStrategyClassStr
     * @return
     * @throws ClassNotFoundException
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public static IdStrategy getIdStrategy(String idStrategyClassStr) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        IdStrategy idStrategy = null;
        if(!StringUtils.isEmpty(idStrategyClassStr)){
            idStrategy = (IdStrategy)Class.forName(idStrategyClassStr).newInstance();
        }
        return idStrategy;
    }

}
