package io.renren.common.example.generator;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;


import java.util.function.Function;

/**
 * 小于等于
 */

public class LePredicateGenerator<T> extends AbstractPredicateGenerator<T> {


    @Override
    protected Function getCreateMethod(Function o, String column, Object[] values) {
        if (values.length == 1) {
            Function<QueryWrapper<T>, QueryWrapper<T>> o1 = i -> i.le(column, values[0]);
            o = o.andThen(o1);
        } else if (values.length > 1) {
            for (Object value :
                values) {
                Function<QueryWrapper<T>, QueryWrapper<T>> o1 = i -> i.or().le(column, value);
                o = o.andThen(o1);
            }
        }
        return o;
    }
}
