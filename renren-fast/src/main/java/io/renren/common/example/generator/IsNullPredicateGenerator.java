package io.renren.common.example.generator;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.function.Function;

/**
 * 为NULL
 * Created by cwx183898 on 2017/12/17.
 */
public class IsNullPredicateGenerator<T> extends AbstractPredicateGenerator<T> {

    @Override
    protected Function getCreateMethod(Function o, String column, Object[] values) {
        if (values.length == 1) {
            Function<QueryWrapper<T>,QueryWrapper<T>> o1= i -> i.isNull(column);
            o = o.andThen(o1);
        } else if (values.length > 1) {
            for (Object value:
                values ) {
                Function<QueryWrapper<T>,QueryWrapper<T>> o1 = i -> i.or().isNull(column);
                o = o.andThen(o1);
            }
        }
        return o;
    }
}
