//package io.dreamtale.mybatisplus.extend.common.base.controller.supports;
//
//
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.io.*;
//import java.util.Arrays;
//
//import io.renren.common.base.controller.method.support.annotations.EntityParam;
//import io.swagger.annotations.ApiOperation;
//
///**
// * @program: dreamtale-web-framework-extends
// * @description:
// * @author: AndyLiu
// * @create: 2018-04-23 20:05
// **/
//public abstract class DefaultCrudController<T, ID extends Serializable> extends DefaultReadController<T, ID> {
//
//    @ApiOperation(value = "保存", notes = "参数为JSON数据对象")
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public T save(@EntityParam T entity) {
//        boolean result = getService().save(entity);
//        return result?entity:null;
//    }
//
//    @ApiOperation(value = "批量删除", notes = "根据ID集批量删除，返回true表示删除成功")
//    @RequestMapping(value = "/deletes", method = RequestMethod.POST)
//    public boolean deletes(ID[] ids) {
//        getService().removeByIds(Arrays.asList(ids));
//        return true;
//    }
//
//    @ApiOperation(value = "删除", notes = "根据ID删除，返回true表示删除成功")
//    @RequestMapping(value = "/delete", method = RequestMethod.POST)
//    public boolean delete(ID id) {
//        getService().removeById(id);
//        return true;
//    }
//}
