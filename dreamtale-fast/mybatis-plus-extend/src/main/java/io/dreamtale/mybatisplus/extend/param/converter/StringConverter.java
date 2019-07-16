package io.dreamtale.mybatisplus.extend.param.converter;

public class StringConverter extends AbstractConverter {
    public Object[] transform(Object src) {
        Object[] values = createArray(src);
        String[] results = new String[values.length];
        for (int i = 0; i < values.length; i++) {
            results[i] = values[i].toString();
        }
        return results;
    }
}
