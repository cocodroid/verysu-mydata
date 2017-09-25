package com.verysu.mydata.id;

import com.google.common.base.Preconditions;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

/**
 * ${DESCRIPTION}
 *
 * @author Cocodroid
 * @create 2017-08-21 14:24
 */
public abstract class AbstractIdStrategy implements IdStrategy{

    public abstract Object generateId();

    @Override
    public Set<Object> generateIds(int count) {
        Preconditions.checkArgument(count > 0,
                "generateId count must greater than 0");
        Set<Object> ids = new TreeSet<>();
        for (int i = 0; i < count; i++) {
            Object id = generateId();
            ids.add(id);
        }
        int totalIds = ids.size();
        if(totalIds < count){//id出现重复
            ids.addAll(generateIds(count-totalIds));
        }
        return ids;
    }
}
