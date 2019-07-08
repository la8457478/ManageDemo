package io.renren.common.example;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import io.renren.common.example.param.Param;


/**
 * Created by cwx183898 on 2017/8/18.
 */
public class DynamicSpecification<T> {
    private Map<String, Object> params;
    private boolean distinct = false;
    private Class<?> clazz = null;

    public DynamicSpecification(Map<String, Object> params) {
        this.params = params;
    }

    public DynamicSpecification(Map<String, Object> params, boolean distinct) {
        this.params = params;
        this.distinct = distinct;
    }

    public QueryWrapper<T> toPredicate() {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        if (params != null) {
            Set<Map.Entry<String, Object>> entries = params.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                wrapper = createPredicate(wrapper, entry);
            }
        }
        return wrapper;
    }

    private QueryWrapper<T> createPredicate(QueryWrapper<T> wrapper, Map.Entry<String, Object> entry) {
        Param param = new Param(entry);
        //根据判断符号获取sql生成规则类
        Function o = PredicateGeneratorFactory.getGenerator(param.getOper()).toPredicate(
            param);
        if (wrapper != null) {
            if (param.getJoin().equals("or")) {
                wrapper = wrapper.or(o);
            } else {
                wrapper.and(o);
            }
        } else {
            wrapper.and(o);
        }
        return wrapper;
    }

}