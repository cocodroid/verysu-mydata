package com.verysu.mydata.test;

import com.verysu.mydata.core.DefaultCopy;
import com.verysu.mydata.dao.DestData;
import com.verysu.mydata.dao.SourceData;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Calendar;

/**
 * ${DESCRIPTION}
 *
 * @author Cocodroid
 * @create 2017-08-19 19:08
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class TestMyData {

    @Resource
    private SourceData sourceData;
    @Resource
    private DestData destData;
    @Resource
    private DefaultCopy defaultCopy;
    @Test
    public void TestCopy() throws Exception {
        defaultCopy.setSourceDataInterface(sourceData);
        defaultCopy.setDestDataInterface(destData);
        defaultCopy.copy();
    }

}
