package io.renren.common.example.generator;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.function.Function;

/**
 * 为空
 * Created by AndyLiu on 2017/12/17.
 */
public class IsEmptyPredicateGenerator<T> extends AbstractPredicateGenerator<T> {

    @Override
    protected Function getCreateMethod(Function o, String column, Object[] values) {
        if (values.length == 1) {
            Function<QueryWrapper<T>,QueryWrapper<T>> o1 = i -> i.eq(column,"");
            o = o==null?o1:o.andThen(o1);
        } else if (values.length > 1) {
            for (Object value:
                    values ) {
                Function<QueryWrapper<T>,QueryWrapper<T>> o1 = i -> i.eq(column,"");
                o = o==null?o1:o.andThen(o1);
            }
        }
        return o;
    }
}
