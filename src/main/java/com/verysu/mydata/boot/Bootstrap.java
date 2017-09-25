package com.verysu.mydata.boot;

import com.verysu.mydata.core.DefaultCopy;
import com.verysu.mydata.dao.DestData;
import com.verysu.mydata.dao.SourceData;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.PropertyConfigurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.net.URL;

/**
 * ${DESCRIPTION}
 *
 * @author Cocodroid
 * @create 2017-09-01 15:02
 */
public final class Bootstrap {

    private static final Log log = LogFactory.getLog(Bootstrap.class);

    public static void main(String[] args) {
        log.info("-------------Bootstrap begin copy-------------");
        //加载日志log4j配置文件
        URL log4jPath=ClassLoader.getSystemResource("log4j.properties");
        log.info("-------------Bootstrap 加载日志log4j配置文件-------------");
        PropertyConfigurator.configure(log4jPath);
        ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        SourceData sourceData = ctx.getBean(SourceData.class);
        DestData destData = ctx.getBean(DestData.class);
        DefaultCopy defaultCopy = ctx.getBean(DefaultCopy.class);
        defaultCopy.setSourceDataInterface(sourceData);
        defaultCopy.setDestDataInterface(destData);
        log.info("-------------Bootstrap copying-------------");
        defaultCopy.copy();
        log.info("-------------Bootstrap end copy-------------");

    }
}
