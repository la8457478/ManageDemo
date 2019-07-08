package io.renren.common.example.param.converter;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public final class ConverterManager {
    private static final Log logger = LogFactory.getLog(ConverterManager.class);

    private static Map<Class, AbstractConverter> transformers = new HashMap<Class, AbstractConverter>(0);

    private ConverterManager() {
    }

    public static AbstractConverter get(Class<?> type) {
        return transformers.get(type);
    }

    static {
        transformers.put(Number.class, new NumberConverter());
        transformers.put(Integer.class, transformers.get(Number.class));
        transformers.put(Float.class, transformers.get(Number.class));
        transformers.put(Double.class, transformers.get(Number.class));
        transformers.put(Long.class, transformers.get(Number.class));
        transformers.put(Short.class, transformers.get(Number.class));
        transformers.put(Byte.class, transformers.get(Number.class));
        transformers.put(Boolean.class, new BooleanConverter());
        transformers.put(Date.class, new DateConverter());
        transformers.put(String.class, new StringConverter());
        transformers.put(int.class, transformers.get(Number.class));
        transformers.put(float.class, transformers.get(Number.class));
        transformers.put(double.class, transformers.get(Number.class));
        transformers.put(long.class, transformers.get(Number.class));
        transformers.put(short.class, transformers.get(Number.class));
        transformers.put(byte.class, transformers.get(Number.class));
        transformers.put(boolean.class, transformers.get(Boolean.class));
    }
}
