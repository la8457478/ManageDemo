package io.dreamtale.mybatisplus.extend.param.converter;

public abstract class AbstractConverter {
    public abstract Object[] transform(Object src);

    public Object[] createArray(Object value) {
        Object[] values = new Object[0];
        if (value != null) {
            if (value.getClass().isArray()) {
                values = (Object[]) value;
            } else {
                if (value != null && value.toString().length() > 0) {
                    values = new Object[]{value};
                }
            }
        }
        return values;
    }
}
