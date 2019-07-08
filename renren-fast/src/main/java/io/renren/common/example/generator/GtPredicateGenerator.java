package io.renren.common.example.generator;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.function.Function;

/**
 * 大于
 */

public class GtPredicateGenerator<T> extends AbstractPredicateGenerator<T> {

    protected QueryWrapper getCreateMethod(QueryWrapper<T> wrapper, String column, Object[] values) {
        if (values.length == 1) {
            wrapper.gt(column, values[0]);
        } else if (values.length > 1) {
            Function<QueryWrapper<T>,QueryWrapper<T>> o=null;
            for (Object value:
                    values ) {
                Function<QueryWrapper<T>,QueryWrapper<T>> o1 = i -> i.gt(column,value);
                o = o.andThen(o1);
            }
            wrapper.nested(o);
        }
        return wrapper;
    }

    @Override
    protected Function getCreateMethod(Function o, String column, Object[] values) {
        if (values.length == 1) {
            Function<QueryWrapper<T>,QueryWrapper<T>> o1= i -> i.gt(column,values[0]);
            o = o.andThen(o1);
        } else if (values.length > 1) {
            for (Object value:
                values ) {
                Function<QueryWrapper<T>,QueryWrapper<T>> o1 = i -> i.or().gt(column,value);
                o = o.andThen(o1);
            }
        }
        return o;
    }
}