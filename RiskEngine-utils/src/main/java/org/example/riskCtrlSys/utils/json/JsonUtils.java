package org.example.riskCtrlSys.utils.json;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.TypeReference;

import java.util.List;
import java.util.Map;

public class JsonUtils {

    public static String obj2JsonStr(Object obj) {
        return JSON.toJSONString(obj);
    }


    public static <T> T jsonStr2Obj(String json, Class<T> cls) {
        return JSON.parseObject(json, cls);
    }

    public static <K, V> Map<K, List<V>> jsonStr2Map(String json, Class<K> k, Class<V> v) {

        return JSON.parseObject(json, new TypeReference<Map<K, List<V>>>(k, v) {
        });
    }


    public static <T> List<T> jsonStr2List(String json, Class<T> T) {
        return JSON.parseArray(json, T);
    }
}
