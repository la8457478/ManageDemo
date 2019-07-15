package io.renren.common.example.generator;


import java.util.function.Function;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * 小于
 */
public class InPredicateGenerator<T> extends AbstractPredicateGenerator<T> {

    @Override
    protected Function getCreateMethod(Function o, String column, Object[] values) {
        Function<QueryWrapper<T>, QueryWrapper<T>> o1 = i -> i.in(column, values);
        o = o==null?o1:o.andThen(o1);
        return o;
    }
}