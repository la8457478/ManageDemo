package io.renren.common.example.param.converter;

public class BooleanConverter extends AbstractConverter {
    @Override
    public Object[] transform(Object src) {
        Object[] values = createArray(src);
        Boolean[] results = new Boolean[values.length];
        for (int i = 0; i < values.length; i++) {
            results[i] = Boolean.parseBoolean(values[i].toString());
        }
        return results;
    }
}
