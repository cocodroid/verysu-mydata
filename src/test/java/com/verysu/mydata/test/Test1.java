package com.verysu.mydata.test;

import com.verysu.mydata.core.AbstractCopy;
import com.verysu.mydata.core.CopyInterface;
import com.verysu.mydata.core.DataException;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author Cocodroid
 * @create 2017-07-19 14:08
 */
public class Test1 {
    public static void main(String[] args){
        CopyInterface a = new AbstractCopy() {
//            @Override
//            public List select() {
//                try {
//                    Thread.sleep(123);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                return null;
//            }
//
//            @Override
//            public void save(List datas) {
//                try {
//                    Thread.sleep(213);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void delete(Object obj) throws DataException {
//                try {
//                    Thread.sleep(12);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void update(Object obj) throws DataException {
//
//            }
        };
        try {
            a.copy();
        } catch (DataException e) {
            e.printStackTrace();
        }
    }
}
