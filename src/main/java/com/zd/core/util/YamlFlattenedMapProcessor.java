package com.zd.core.util;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * YAML 扁平化映射处理器
 *
 * @author Tao.Gan
 * @date 2023/12/04
 */
public class YamlFlattenedMapProcessor implements YamlMapProcessor {

    public Map<String, Object> process(Map<String, Object> source) {
        return getFlattenedMap(source);
    }

    protected final Map<String, Object> getFlattenedMap(Map<String, Object> source) {
        Map<String, Object> result = new LinkedHashMap<>();
        buildFlattenedMap(result, source, null);
        return result;
    }

    private void buildFlattenedMap(Map<String, Object> result, Map<String, Object> source, @Nullable String path) {
        source.forEach((key, value) -> {
            if (StringUtils.hasText(path)) {
                if (key.startsWith("[")) {
                    key = path + key;
                } else {
                    key = path + '.' + key;
                }
            }
            if (value instanceof String) {
                buildString(result, key, (String) value);
            } else if (value instanceof Map) {
                // Need a compound key
                @SuppressWarnings("unchecked")
                Map<String, Object> map = (Map<String, Object>) value;
                buildMap(result, key, map);
            } else if (value instanceof Collection) {
                // Need a compound key
                @SuppressWarnings("unchecked")
                Collection<Object> collection = (Collection<Object>) value;
                buildCollection(result, key, collection);
            } else {
                result.put(key, (value != null ? value : ""));
            }
        });
    }

    protected void buildString(Map<String, Object> result, String key, String value) {
        result.put(key, value);
    }

    protected void buildMap(Map<String, Object> result, String key, Map<String, Object> value) {
        buildFlattenedMap(result, value, key);
    }

    protected void buildCollection(Map<String, Object> result, String key, Collection<Object> value) {
        if (value.isEmpty()) {
            result.put(key, "");
        } else {
            int count = 0;
            for (Object object : value) {
                buildFlattenedMap(result, Collections.singletonMap("[" + (count++) + "]", object), key);
            }
        }
    }
}
