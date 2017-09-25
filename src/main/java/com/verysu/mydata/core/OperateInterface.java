package com.verysu.mydata.core;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Cocodroid
 * @create 2017-07-19 16:14
 */
public interface OperateInterface<T> {
    public List<T> select() throws DataException;
    public void delete(T obj) throws DataException;
    public void update(T obj) throws DataException;
    public void save(List<T> datas) throws DataException, IllegalAccessException, InstantiationException, ClassNotFoundException;
}
