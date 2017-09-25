package com.verysu.mydata.spring.handler;

import com.verysu.mydata.spring.parser.DestTableBeanDefinitionParser;
import com.verysu.mydata.spring.parser.MyDataBeanDefinitionParser;
import com.verysu.mydata.spring.parser.SourceTableBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * MyData命名空间处理器
 * @author Cocodroid
 * @create 2017-08-19 2:20
 */
public class MyDataNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("mydata",new MyDataBeanDefinitionParser());
        registerBeanDefinitionParser("sourceTable",new SourceTableBeanDefinitionParser());
        registerBeanDefinitionParser("destTable",new DestTableBeanDefinitionParser());
    }
}
