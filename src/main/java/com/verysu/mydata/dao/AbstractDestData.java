package com.verysu.mydata.dao;

import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlInsertStatement;
import com.alibaba.druid.sql.dialect.mysql.parser.MySqlStatementParser;
import com.google.common.base.Preconditions;
import com.verysu.mydata.core.DataException;
import com.verysu.mydata.core.DestDataInterface;
import com.verysu.mydata.id.IdStrategy;
import com.verysu.mydata.spring.DestTable;
import com.verysu.mydata.spring.MyData;
import com.verysu.mydata.util.SqlUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * ${DESCRIPTION}
 *
 * @author Cocodroid
 * @create 2017-08-19 18:54
 */
public abstract class AbstractDestData implements DestDataInterface{

    @Autowired
    private JdbcTemplate destJdbcTemplate;
    @Autowired
    private MyData myData;

    @Override
    public void save(final List datas) throws DataException, IllegalAccessException, InstantiationException, ClassNotFoundException {
        DestTable destTable = myData.getDestTable();
        Preconditions.checkNotNull(destTable,"DestTable must not empty");
        final String sql = destTable.getSql();
        final String idFieldStr = destTable.getIdField();
        final String idStrategyStr = destTable.getIdStrategy();
        //校验IdField是否在SQL里、获得IdField对应的下标
        int idIndex = StringUtils.isEmpty(idFieldStr) ? -1:SqlUtil.idInSqlIndex(idFieldStr,sql);
        final IdStrategy idStrategy = SqlUtil.getIdStrategy(idStrategyStr);
        final int idIndex0 = idIndex;

        destJdbcTemplate.batchUpdate(sql, new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                try {
                    Map rowData = (Map)datas.get(i);
                    Object[] keys = (Object[])rowData.keySet().toArray();
                    Map map = SqlUtil.columnMapValue(sql);
                    int index;
                    int noSet = 0;
                    for (int j = 0; j < keys.length; j++) {
                        index = j+1-noSet;
                        Object column = keys[j];
                        Object obj = rowData.get(column);
                        if(idIndex0 != -1 && idIndex0 == j+1){//指定ID生成
                            Preconditions.checkNotNull(idStrategy);
                            ps.setObject(index,idStrategy.generateId());
                        }else {
                            String value = (String) map.get(column);
                            if("?".equals(value)){//是占位符
                                ps.setObject(index, obj);
                            }else{
                                //没有占位符，下标-1
                                noSet++;
                            }
                        }
                    }
                } catch (DataException e) {
                    e.printStackTrace();
                }
            }
            @Override
            public int getBatchSize() {
                return datas.size();
            }
        });
    }

    @Override
    public void delete(Object obj) throws DataException {

    }

    @Override
    public void update(Object obj) throws DataException {

    }

    @Override
    public List select() throws DataException {
        return null;
    }


    public static void main(String[] args) throws DataException {
        String sql = "insert into user3(id,username,age,createTime) values(?,?,?,NOW())";
        System.out.println(SqlUtil.idInSqlIndex("id","insert into user3(id,username,age,createTime) values(?,?,?,NOW())")
        );
        MySqlStatementParser  parser = new MySqlStatementParser("insert into user3(id,username,age,createTime) values(?,?,?,NOW())");
        SQLStatement sqlStatement = parser.parseInsert();
        MySqlInsertStatement mySqlInsertStatement = (MySqlInsertStatement)sqlStatement;
        System.out.println(mySqlInsertStatement.getColumns());
        System.out.println(mySqlInsertStatement.getValues().getValues());
        System.out.println(SqlUtil.columnMapValue(sql));
    }
}
