package com.verysu.mydata.spring.parser;

import com.verysu.mydata.spring.DestTable;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSimpleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * ${DESCRIPTION}
 *
 * @author Cocodroid
 * @create 2017-08-19 2:42
 */
public class DestTableBeanDefinitionParser extends AbstractSimpleBeanDefinitionParser {

    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        super.doParse(element, parserContext, builder);
        String sql = element.getAttribute("sql");
        if(!StringUtils.isEmpty(sql)){
            builder.addPropertyValue("sql",sql);
        }
    }

    @Override
    protected Class<DestTable> getBeanClass(Element element) {
        return DestTable.class;
    }
}
