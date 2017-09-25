package com.verysu.mydata.test;

import com.verysu.mydata.id.IdStrategy;
import com.verysu.mydata.id.SnowflakeIdStrategy;
import com.verysu.mydata.id.TimestampAndRandomCharIdStrategy;

import java.util.Set;

/**
 * ${DESCRIPTION}
 *
 * @author Cocodroid
 * @create 2017-08-21 14:31
 */
public class TestId {
    public static void main(String[] args) {
        IdStrategy idStrategy = null;
//        idStrategy = new TimestampAndRandomCharIdStrategy();
//        Set set1 = idStrategy.generateIds(10);
//        System.out.println(set1.size());

        idStrategy = new SnowflakeIdStrategy(1L);
        Set set2 = idStrategy.generateIds(1000);
        System.out.println(set2);
        System.out.println(set2.size());
    }
}
