package com.verysu.mydata.id;

import java.util.Set;

/**
 * ID主键生成策略接口
 * @author Cocodroid
 * @create 2017-08-21 10:57
 */
public interface IdStrategy {

    public Object generateId();

    /**
     * 生成批量Id,可以防止id重复（去重）
     * @param count
     * @return
     */
    public Set<Object> generateIds(int count);
}
