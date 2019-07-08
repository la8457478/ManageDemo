package io.renren.common.base.service;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.QueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.UpdateChainWrapper;

import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import io.renren.common.example.DynamicSpecification;

public interface IBaseService<T> {
    boolean save(T entity);

    @Transactional(
        rollbackFor = {Exception.class}
    )
    default boolean saveBatch(Collection<T> entityList) {
        return this.saveBatch(entityList, 1000);
    }

    boolean saveBatch(Collection<T> entityList, int batchSize);

    @Transactional(
        rollbackFor = {Exception.class}
    )
    default boolean saveOrUpdateBatch(Collection<T> entityList) {
        return this.saveOrUpdateBatch(entityList, 1000);
    }

    boolean saveOrUpdateBatch(Collection<T> entityList, int batchSize);

    boolean removeById(Serializable id);

    boolean removeByMap(Map<String, Object> columnMap);

    boolean remove(Map<String, Object> params);

    boolean removeByIds(Collection<? extends Serializable> idList);

    boolean updateById(T entity);

    boolean update(T entity, Map<String, Object> params);

    default boolean update(Map<String, Object> params) {
        return this.update(null, params);
    }

    @Transactional(
        rollbackFor = {Exception.class}
    )
    default boolean updateBatchById(Collection<T> entityList) {
        return this.updateBatchById(entityList, 1000);
    }

    boolean updateBatchById(Collection<T> entityList, int batchSize);

    boolean saveOrUpdate(T entity);

    T getById(Serializable id);

    Collection<T> listByIds(Collection<? extends Serializable> idList);

    Collection<T> listByMap(Map<String, Object> columnMap);

    default T getOne(Map<String, Object> params) {
        return this.getOne(params, true);
    }

    T getOne(Map<String, Object> params, boolean throwEx);

    Map<String, Object> getMap(Map<String, Object> params);

    <V> V getObj(Map<String, Object> params, Function<? super Object, V> mapper);

    int count(Map<String, Object> params);

    default int count() {
        return this.count(null);
    }

    List<T> list(Map<String, Object> params);

    default List<T> list() {
        return this.list(null);
    }

    IPage<T> page(IPage<T> page, Map<String, Object> params);

    default IPage<T> page(IPage<T> page) {
        return this.page(page, null);
    }

    List<Map<String, Object>> listMaps(Map<String, Object> params);

    default List<Map<String, Object>> listMaps() {
        return this.listMaps(null);
    }

    default List<Object> listObjs() {
        return this.listObjs(Function.identity());
    }

    default <V> List<V> listObjs(Function<? super Object, V> mapper) {
        return this.listObjs(null, mapper);
    }

    default List<Object> listObjs(Map<String, Object> params) {
        return this.listObjs(params, Function.identity());
    }

    <V> List<V> listObjs(Map<String, Object> params, Function<? super Object, V> mapper);

    IPage<Map<String, Object>> pageMaps(IPage<T> page, Map<String, Object> params);

    default IPage<Map<String, Object>> pageMaps(IPage<T> page) {
        return this.pageMaps(page, null);
    }

    BaseMapper<T> getBaseMapper();

    default QueryChainWrapper<T> query() {
        return new QueryChainWrapper(this.getBaseMapper());
    }

    default LambdaQueryChainWrapper<T> lambdaQuery() {
        return new LambdaQueryChainWrapper(this.getBaseMapper());
    }

    default UpdateChainWrapper<T> update() {
        return new UpdateChainWrapper(this.getBaseMapper());
    }

    default LambdaUpdateChainWrapper<T> lambdaUpdate() {
        return new LambdaUpdateChainWrapper(this.getBaseMapper());
    }
    default Wrapper<T> generateWrapper(Map<String, Object> params, Boolean distinct) {
        DynamicSpecification<T> dynamicSpecification = new DynamicSpecification<T>(params, distinct);
        if(params==null){
            return Wrappers.emptyWrapper();
        }
        return dynamicSpecification.toPredicate();
    }

    default Wrapper<T> generateWrapper(Map<String, Object> params) {
        DynamicSpecification<T> dynamicSpecification = new DynamicSpecification<T>(params);
        if(params==null){
            return Wrappers.emptyWrapper();
        }
        return dynamicSpecification.toPredicate();
    }
}