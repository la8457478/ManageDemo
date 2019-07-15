package io.dreamtale.mybatisplus.extend.param.converter;

import org.apache.commons.lang.math.NumberUtils;

public class NumberConverter extends AbstractConverter {
    public Object[] transform(Object src) {
        Object[] values = createArray(src);
        Number[] results = new Number[values.length];
        for (int i = 0; i < values.length; i++) {
            results[i] = NumberUtils.createNumber(values[i].toString());
        }
        return results;
    }
}
