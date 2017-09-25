package com.verysu.mydata.dao;

import com.verysu.mydata.core.DataException;
import com.verysu.mydata.core.SourceDataInterface;
import com.verysu.mydata.spring.MyData;
import com.verysu.mydata.spring.SourceTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Cocodroid
 * @create 2017-08-19 18:40
 */
public abstract class AbstractSourceData implements SourceDataInterface{

    @Autowired
    private JdbcTemplate srcJdbcTemplate;
    @Autowired
    private MyData myData;

    @Override
    public List select() throws DataException {
        String sql = myData.getSourceTable().getSql();
        Integer batchSize = myData.getBatchSize();
        return srcJdbcTemplate.queryForList(sql + " LIMIT "+batchSize);
    }

    public List select(int offset) throws DataException {
        SourceTable sourceTable = myData.getSourceTable();
        String sql = sourceTable.getSql();
        Integer batchSize = myData.getBatchSize();
        if(sourceTable.isDelete()){
            sql = sql + " LIMIT "+ batchSize;
        }else{
            sql = sql + " LIMIT "+ offset + "," + batchSize;
        }
        return srcJdbcTemplate.queryForList(sql);
    }

    @Override
    public void update(Object obj) throws DataException {

    }

    @Override
    public void save(List datas) throws DataException {

    }

    @Override
    public void delete(Object obj) throws DataException {
        SourceTable sourceTable = myData.getSourceTable();
        if(!sourceTable.isDelete()) return;
        String sql = myData.getSourceTable().getSql();
        Integer batchSize = myData.getBatchSize();
        if(!StringUtils.isEmpty(sql)){
            int fromIndex = sql.toLowerCase().indexOf("from");
            sql = sql.substring(fromIndex);
            sql = "DELETE " + sql + " LIMIT " + batchSize;
        }
        int count = srcJdbcTemplate.update(sql);
        System.out.println("delete count:"+count);
    }


}
