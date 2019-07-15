package io.renren.modules.app.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;

import io.dreamtale.mybatisplus.extend.common.utils.PageUtils;
import io.dreamtale.mybatisplus.extend.common.utils.Query;
import io.dreamtale.mybatisplus.extend.common.utils.R;
import io.dreamtale.mybatisplus.extend.common.validator.ValidatorUtils;
import io.renren.modules.app.form.LoginForm;
import io.renren.modules.app.service.UserService;
import io.renren.modules.app.utils.JwtUtils;
import io.renren.modules.sys.entity.SysUserEntity;
import io.renren.modules.sys.service.SysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * APP登录授权
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:31
 */
@RestController
@RequestMapping("/app")
@Api("APP登录接口")
public class AppLoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private SysUserService sysUserService;

    /**
     * 登录
     */
    @PostMapping("login")
    @ApiOperation("登录")
    public R login(@RequestBody LoginForm form){
        //表单校验
        ValidatorUtils.validateEntity(form);

        //用户登录
        long userId = userService.login(form);

        //生成token
        String token = jwtUtils.generateToken(userId);

        Map<String, Object> map = new HashMap<>();
        map.put("token", token);
        map.put("expire", jwtUtils.getExpire());

        return R.ok(map);
    }
    /**
     * 所有用户列表
     */
    @GetMapping("/list")
    public R list(@RequestParam Long userId,@RequestParam Long parentId) {

        Map map = new HashMap();
        map.put("createUserId",userId);
        map.put("parentId",parentId);
        //只有超级管理员，才能查看所有管理员列表
        IPage<SysUserEntity> pageable = new Query<SysUserEntity>(map).getPage();

        PageUtils page = sysUserService.queryPage(map, pageable);

        return R.ok().put("page", page);
    }
}
