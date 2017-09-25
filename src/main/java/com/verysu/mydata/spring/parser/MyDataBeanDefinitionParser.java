package com.verysu.mydata.spring.parser;

import com.verysu.mydata.spring.MyData;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.springframework.util.xml.DomUtils;
import org.w3c.dom.Element;

/**
 * ${DESCRIPTION}
 *
 * @author Cocodroid
 * @create 2017-08-19 2:22
 */
public class MyDataBeanDefinitionParser extends AbstractSimpleBeanDefinitionParser {

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        super.doParse(element, parserContext, builder);
//        builder.addPropertyValue("sourceTable",parserContext.getDelegate().parseCustomElement(
//                DomUtils.getChildElementByTagName(element,"sourceTable"), builder.getRawBeanDefinition()));
//        builder.addPropertyValue("destTable",parserContext.getDelegate().parseCustomElement(
//                DomUtils.getChildElementByTagName(element,"destTable"), builder.getRawBeanDefinition()));
        builder.addPropertyValue("sourceTable",parserContext.getDelegate().parseCustomElement(
                DomUtils.getChildElementByTagName(element,"sourceTable"), builder.getRawBeanDefinition()));
        builder.addPropertyValue("destTable",parserContext.getDelegate().parseCustomElement(
                DomUtils.getChildElementByTagName(element,"destTable"), builder.getRawBeanDefinition()));
        String batchSize = element.getAttribute("batchSize");
        if(!StringUtils.isEmpty(batchSize)){
            builder.addPropertyValue("batchSize",batchSize);
        }
    }

    @Override
    protected Class<MyData> getBeanClass(Element element) {
        return MyData.class;
    }
}
