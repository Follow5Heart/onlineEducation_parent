package com.zty.onlineedu.common.base.utils;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

/**
 * @Author zty
 * @Date 2023/1/19 15:03
 * json处理工具类
 */
public class JsonUtils {
    private static final ObjectMapper MAPPER = new ObjectMapper();

    public JsonUtils()
    {
    }

    public static String objectToJson(Object data)
    {
        try{
            String string = MAPPER.writeValueAsString(data);
            return string;
        }catch (JsonProcessingException e){
            e.printStackTrace();
            return null;
        }

    }

    public static Object jsonToPojo(String jsonData, Class beanType)
    {
        try {
            Object t = MAPPER.readValue(jsonData, beanType);
            return t;

        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    public static Map<String, Object> jsonToMap(String jsonString) {
        Map<String, Object> map = JSONObject.parseObject(jsonString, new TypeReference<Map<String, Object>>() {});
        return map;
    }

    public static List jsonToList(String jsonData, Class beanType)
    {
        try{
            JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, new Class[] {
                    beanType
            });
            List list = (List)MAPPER.readValue(jsonData, javaType);
            return list;
        }catch ( Exception e){
            e.printStackTrace();
            return null;
        }

    }
}
