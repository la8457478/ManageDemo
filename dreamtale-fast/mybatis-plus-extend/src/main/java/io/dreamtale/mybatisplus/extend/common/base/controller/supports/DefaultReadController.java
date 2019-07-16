//package io.dreamtale.mybatisplus.extend.common.base.controller.supports;
//
//
//import com.baomidou.mybatisplus.core.metadata.IPage;
//
//import org.apache.shiro.SecurityUtils;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//
//import java.io.*;
//import java.utils.LinkedHashMap;
//import java.utils.List;
//
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiOperation;
//
///**
// * @program: dreamtale-web-framework-extends
// * @description:
// * @author: AndiLiu
// * @create: 2018-04-23 20:05
// **/
//public abstract class DefaultReadController<T, ID extends Serializable> {
//
//
//    protected SysUserEntity getUser() {
//        return (SysUserEntity) SecurityUtils.getSubject().getPrincipal();
//    }
//
//    protected Long getUserId() {
//        return getUser().getUserId();
//    }
//
//    public DefaultReadController() {
//    }
//
//    protected IBaseService<T> getService() {
//        return null;
//    }
//
//    @ApiOperation(value = "根据数据ID查询单个数据", notes = "如果指定ID的数据不存在，则返回NULL")
//    @ApiImplicitParam(name = "id", value = "查询", dataType = "int", paramType = "query")
//    @RequestMapping(value = "/get_by_id", method = RequestMethod.POST)
//    public T getById(ID id) {
//        T data = getService().getById(id);
//        return data;
//    }
//
//    @ApiOperation(value = "获取所有数据", notes = "返回数据中的data为一个数据列表")
//    @RequestMapping(value = "/all", method = RequestMethod.POST)
//    public List<T> all() {
//        List<T> data = getService().list(null);
//        return data;
//    }
//
//    @ApiOperation(value = "综合查询接口", notes = "返回数据中的data为一个数据列表<br/>此函数有2组参数，第一组是查询参数，以params开头，第二组为分页参数，以pageable开头<br/>参数示例如下（支持form data或queryString），<br/>\n" +
//        "&nbsp;&nbsp;&nbsp;&nbsp;params.username_eq:maomao(表示username等于maomao,eq表示等于)<br/>\n" +
//        "&nbsp;&nbsp;&nbsp;&nbsp;params.age_gt:10(表示age大于10，gt表示大于，ge为大于等于，le为小于等于，lt为小于)<br/>\n" +
//        "&nbsp;&nbsp;&nbsp;&nbsp;params.xxx_like:val%(表示用某个属性做LIKE比较,比较值为val%，另外支持notlike)<br/>\n" +
//        "&nbsp;&nbsp;&nbsp;&nbsp;pageable.page:0(当前页)<br/>\n" +
//        "&nbsp;&nbsp;&nbsp;&nbsp;pageable.start:0(起始记录)<br/>\n" +
//        "&nbsp;&nbsp;&nbsp;&nbsp;pageable.size:25(共取多少条数据)<br/>\n" +
//        "&nbsp;&nbsp;&nbsp;&nbsp;pageable.sort:【{\"property\":\"cdt\",\"direction\":\"ASC\"}】(用cdt降序排序，支持多组排序)")
//    @RequestMapping(value = "/query", method = RequestMethod.POST)
//    public IPage<T> query(@MapParam LinkedHashMap<String, Object> params, @EntityParam IPage<T> pageable) {
//        IPage<T> page = getService().page(pageable, params);
//        return page;
//    }
//
//    @ApiOperation(value = "去重复的综合查询接口", notes = "返回数据中的data为一个数据列表<br/>此函数有2组参数，第一组是查询参数，以params开头，第二组为分页参数，以pageable开头<br/>参数示例如下（支持form data或queryString），<br/>\n" +
//        "&nbsp;&nbsp;&nbsp;&nbsp;params.username_eq:maomao(表示username等于maomao,eq表示等于)<br/>\n" +
//        "&nbsp;&nbsp;&nbsp;&nbsp;params.age_gt:10(表示age大于10，gt表示大于，ge为大于等于，le为小于等于，lt为小于)<br/>\n" +
//        "&nbsp;&nbsp;&nbsp;&nbsp;params.xxx_like:val%(表示用某个属性做LIKE比较,比较值为val%，另外支持notlike)<br/>\n" +
//        "&nbsp;&nbsp;&nbsp;&nbsp;pageable.page:0(当前页)<br/>\n" +
//        "&nbsp;&nbsp;&nbsp;&nbsp;pageable.start:0(起始记录)<br/>\n" +
//        "&nbsp;&nbsp;&nbsp;&nbsp;pageable.size:25(共取多少条数据)<br/>\n" +
//        "&nbsp;&nbsp;&nbsp;&nbsp;pageable.sort:【{\"property\":\"cdt\",\"direction\":\"ASC\"}】(用cdt降序排序，支持多组排序)")
//    @RequestMapping(value = "/query_distinct", method = RequestMethod.POST)
//    public IPage<T> queryDistinct(@MapParam LinkedHashMap<String, Object> params, @EntityParam IPage<T> pageable) {
//        IPage<T> page = getService().page(pageable, params);
//        return page;
//    }
//}
