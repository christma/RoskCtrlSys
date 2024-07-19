package org.example.riskCtrlSys.utils.common;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class CommonUtil {
    public static <T> T setFieldValue(Class<T> obj, Map<String, String> map) {
        T instance = null;
        try {
            instance = obj.newInstance();
            Field[] fields = obj.getDeclaredFields();
            for (Field field : fields) {
                String name = field.getName();
                String methodName = methodNameFormat(name, "set");
                Method method = null;
                try {
                    method = obj.getMethod(methodName, field.getType());
                } catch (NoSuchMethodException e) {
                    continue;  // 如果方法不存在，跳过这个字段
                }

                String value = map.get(name);
                Object convertedValue = convertValue(field.getType(), value);

                method.invoke(instance, convertedValue);
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return instance;
    }

    public static <T> Object getFieldValue(T obj, String str) {

        Object eventName = null;
        String methodName = null;

        methodName = methodNameFormat(str, "get");
        try {
            Method method = obj.getClass().getMethod(methodName);
            eventName = method.invoke(obj);
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return eventName;
    }


    private static String methodNameFormat(String str, String preStr) {
        String methodName = null;
        if (preStr == null || preStr.isEmpty()) {
            methodName = str;
        } else {
            methodName = preStr + str.substring(0, 1).toUpperCase() + str.substring(1);
        }
        return methodName;
    }


    private static Object convertValue(Class<?> type, String value) {
        if (type == String.class) {
            return value;
        } else if (type == int.class || type == Integer.class) {
            return Integer.parseInt(value);
        } else if (type == long.class || type == Long.class) {
            return Long.parseLong(value);
        } else if (type == double.class || type == Double.class) {
            return Double.parseDouble(value);
        } else if (type == boolean.class || type == Boolean.class) {
            return Boolean.parseBoolean(value);
        }
        // 其他类型需要添加相应的转换逻辑
        throw new IllegalArgumentException("Unsupported field type: " + type);
    }


    public static boolean isExistColumn(ResultSet rs, String column) {
        try {
            if (rs.findColumn(column) > 0) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}