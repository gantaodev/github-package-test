package com.zd.core.util;


import java.util.Map;

/**
 * YAML 扁平化映射
 *
 * @author Tao.Gan
 * @date 2023/12/04
 */
public class YamlFlattenedMap extends HumpsMap<String, Object> {
    public YamlFlattenedMap(Map<String, Object> m) {
        super(new YamlFlattenedMapProcessor().process(m));
    }

}