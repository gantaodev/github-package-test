package com.zd.core.util;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * YAML 节点映射
 *
 * @author Tao.Gan
 * @date 2023/12/04
 */
public class YamlNodeMap extends LinkedHashMap<String, Object> {
    public YamlNodeMap(Map<String, Object> m) {
        super(new YamlNodeMapProcessor().process(m));
    }


}