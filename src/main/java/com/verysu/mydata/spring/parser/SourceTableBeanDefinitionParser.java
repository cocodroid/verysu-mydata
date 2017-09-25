package com.verysu.mydata.spring.parser;

import com.verysu.mydata.spring.SourceTable;
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
public class SourceTableBeanDefinitionParser extends AbstractSimpleBeanDefinitionParser {
    @Override
    protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
        super.doParse(element, parserContext, builder);
        String sql = element.getAttribute("sql");
        String delete = element.getAttribute("delete");
        if(!StringUtils.isEmpty(sql)){
            builder.addPropertyValue("sql",sql);
        }
        if(!StringUtils.isEmpty(delete)){
            builder.addPropertyValue("delete",Boolean.parseBoolean(delete));
        }
    }

    @Override
    protected Class<SourceTable> getBeanClass(Element element) {
        return SourceTable.class;
    }
}
