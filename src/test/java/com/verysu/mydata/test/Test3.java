package com.verysu.mydata.test;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

/**
 * ${DESCRIPTION}
 *
 * @author Cocodroid
 * @create 2017-08-18 16:33
 */
public class Test3 {

    public static void main(String[] args) {

        String[] fields = new String[]{"id","name","age"};
        final Map<String,Object> map = new HashMap<>();
        map.put("id",123);
        map.put("name","bob");
        map.put("age",23);
        List<Map> datas = new ArrayList<>();
        datas.add(new TreeMap(map));
        System.out.println(datas);

        //查询
        JdbcTemplate template = new JdbcTemplate(null);
        String sql = "";
        Object[] params = new Object[]{"","",12};
        final List<Map<String,Object>> resultDatas = template.queryForList(sql,params);

        //插入
        template.batchUpdate("", new BatchPreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Map rowData = resultDatas.get(i);
                for (int j = 0; j < rowData.size(); j++) {
                    ps.setObject(j+1,rowData.get(j));
                }
            }
            @Override
            public int getBatchSize() {
                return resultDatas.size();
            }
        });
    }

    /**
     <mydata>
        <batchSize></batchSize>
        <sourceTable type="select" sql=""></sourceTable>
        <destTable type="insert" sql=""></destTable>
        <source type="select" fields="" table="" ></source>
        <dest type="insert" fields="" table="" </dest>
     </mydata>
    */
}
