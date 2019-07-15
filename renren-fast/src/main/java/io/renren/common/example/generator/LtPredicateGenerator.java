package io.renren.common.example.generator;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.function.Function;

/**
 * 小于
 */
public class LtPredicateGenerator<T> extends AbstractPredicateGenerator<T> {


    @Override
    protected Function getCreateMethod(Function o, String column, Object[] values) {
        if (values.length == 1) {
            Function<QueryWrapper<T>, QueryWrapper<T>> o1 = i -> i.lt(column, values[0]);
            o = o==null?o1:o.andThen(o1);
        } else if (values.length > 1) {
            for (Object value :
                values) {
                Function<QueryWrapper<T>, QueryWrapper<T>> o1 = i -> i.or().lt(column, value);
                o = o==null?o1:o.andThen(o1);
            }
        }
        return o;
    }
}