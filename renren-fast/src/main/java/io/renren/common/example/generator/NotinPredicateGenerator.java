package io.renren.common.example.generator;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.function.Function;

/**
 * 小于
 */
public class NotinPredicateGenerator<T> extends AbstractPredicateGenerator<T> {


    @Override
    protected Function getCreateMethod(Function o, String column, Object[] values) {
        Function<QueryWrapper<T>, QueryWrapper<T>> o1 = i -> i.notIn(column, values);
        o = o.andThen(o1);
        return o;
    }
}