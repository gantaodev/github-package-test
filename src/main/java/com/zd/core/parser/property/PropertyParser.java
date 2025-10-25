package com.zd.core.parser.property;


import com.zd.core.util.StringHelper;
import org.springframework.util.PropertyPlaceholderHelper;

import java.util.Map;
import java.util.Properties;

/**
 * 属性解析器
 *
 * @author gantao
 * @date 2022/11/11
 */
public class PropertyParser {

    private final PropertyPlaceholderHelper placeholderHelper;

    public PropertyParser() {
        placeholderHelper = new PropertyPlaceholderHelper("${", "}");
    }

    public PropertyParser(String placeholderPrefix, String placeholderSuffix) {
        placeholderHelper = new PropertyPlaceholderHelper(placeholderPrefix, placeholderSuffix);
    }

    public String parse(String value, PropertyPlaceholderHelper.PlaceholderResolver placeholderResolver) {
        if (value == null || placeholderResolver == null) {
            return value;
        }
        return placeholderHelper.replacePlaceholders(value, placeholderResolver);
    }

    /**
     * 解析占位符
     *
     * @param value     解析值
     * @param variables 变量
     * @return {@link String}
     */
    public static String parse(String value, Properties variables) {
        if (value == null || variables == null) {
            return value;
        }
        PropertyParser propertyParser = new PropertyParser("${", "}");
        return propertyParser.parse(value, variables::getProperty);
    }

    /**
     * 解析
     *
     * @param value     解析值
     * @param variables 变量
     * @return {@link String}
     */
    public static String parse(String value, Map<String, ?> variables) {
        if (value == null || variables == null) {
            return value;
        }
        PropertyParser propertyParser = new PropertyParser("${", "}");
        return propertyParser.parse(value, placeholderName -> StringHelper.toString(variables.get(placeholderName)));
    }

}
