package com.leolab.zerosys.common.utils;

public class BeanUtils {
    public static <T> T copyObject(Object src, Class<T> targetClazz) {
        if (src == null) {
            return null;
        }
        T targetObject = null;
        try {
            targetObject = targetClazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        org.springframework.beans.BeanUtils.copyProperties(src, targetObject);
        return targetObject;
    }
}
