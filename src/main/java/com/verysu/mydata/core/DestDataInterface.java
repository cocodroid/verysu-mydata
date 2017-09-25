package com.verysu.mydata.core;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Cocodroid
 * @create 2017-07-18 15:48
 */
public interface DestDataInterface<T> extends OperateInterface<T>{
    @Override
    public void save(List<T>datas ) throws DataException, IllegalAccessException, InstantiationException, ClassNotFoundException;

    @Override
    public void update(T obj) throws DataException;
}
