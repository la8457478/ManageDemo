package io.dreamtale.mybatisplus.extend;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import io.dreamtale.mybatisplus.extend.param.Param;
import lombok.extern.slf4j.Slf4j;


/**
 * Created by AndyLiu on 2017/8/18.
 */
@Slf4j
public class DynamicSpecification<T> {
    private Map<String, Object> params;
    private boolean distinct = false;

    public DynamicSpecification(Map<String, Object> params) {
        this.params = params;
    }

    public DynamicSpecification(Map<String, Object> params, boolean distinct) {
        this.params = params;
        this.distinct = distinct;
    }

    public QueryWrapper<T> toPredicate(Class<?> entityType) {
        QueryWrapper<T> wrapper = new QueryWrapper<>();
        if (params != null) {
            Set<Map.Entry<String, Object>> entries = params.entrySet();
            for (Map.Entry<String, Object> entry : entries) {
                wrapper = createPredicate(wrapper, entry, entityType);
            }
        }
        return wrapper;
    }

    private QueryWrapper<T> createPredicate(QueryWrapper<T> wrapper, Map.Entry<String, Object> entry, Class<?> entityType) {
        Param param = new Param(entry);
        Function o = null;
        try {
            entityType.getDeclaredFields();//取得参数类型（对应实体的字段类型）
            Class paramType = entityType.getDeclaredField(param.getPath()).getType();//取得参数类型（对应实体的字段类型）
            //根据判断符号获取sql生成规则类
            o = PredicateGeneratorFactory.getGenerator(param.getOper()).toPredicate(
                param, o, paramType);
        } catch (NoSuchFieldException e) {
            log.error("noSuchFieldException class:{},field :{}", entityType.getName(), param.getPath());
        }
        if (o != null) {
            if (wrapper != null) {
                if (param.getJoin().equals("or")) {
                    wrapper = wrapper.or(o);
                } else {
                    wrapper.and(o);
                }
            } else {
                wrapper.and(o);
            }
        }
        return wrapper;
    }

}