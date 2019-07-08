package io.renren.common.example.generator;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.function.Function;

/**
 * 不等于
 * Created by cwx183898 on 2017/12/17.
 */
public class NePredicateGenerator<T> extends AbstractPredicateGenerator<T> {

    @Override
    protected Function getCreateMethod(Function o, String column, Object[] values) {
        if (values.length == 1) {
            Function<QueryWrapper<T>, QueryWrapper<T>> o1 = i -> i.ne(column, values[0]);
            o = o.andThen(o1);
        } else if (values.length > 1) {
            for (Object value :
                values) {
                Function<QueryWrapper<T>, QueryWrapper<T>> o1 = i -> i.or().ne(column, value);
                o = o.andThen(o1);
            }
        }
        return o;
    }
}
