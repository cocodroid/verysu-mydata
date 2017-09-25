package com.verysu.mydata.core;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Cocodroid
 * @create 2017-07-18 15:48
 */
public interface SourceDataInterface<T> extends OperateInterface<T>{
    @Override
    public List<T> select() throws DataException;

    public List<T> select(int offset) throws DataException;

    @Override
    public void delete(T obj) throws DataException;
}
