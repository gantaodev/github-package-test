package com.zd.core.util;


import java.util.Collection;
import java.util.Map;

/**
 * YAML 节点映射处理器
 *
 * @author Tao.Gan
 * @date 2023/12/04
 */
public class YamlNodeMapProcessor extends YamlFlattenedMapProcessor {

    protected void buildMap(Map<String, Object> result, String key, Map<String, Object> value) {
        result.put(key, value);
        super.buildMap(result, key, value);
    }

    @Override
    protected void buildCollection(Map<String, Object> result, String key, Collection<Object> value) {
        if (!value.isEmpty()) {
            result.put(key, value);
        }
        super.buildCollection(result, key, value);
    }
}
