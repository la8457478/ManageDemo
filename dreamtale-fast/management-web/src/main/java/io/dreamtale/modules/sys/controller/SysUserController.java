package io.dreamtale.modules.sys.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.metadata.IPage;

import io.dreamtale.bean.entity.sys.entity.SysUserEntity;
import io.dreamtale.mybatisplus.extend.common.annotation.SysLog;
import io.dreamtale.mybatisplus.extend.common.utils.Constant;
import io.dreamtale.mybatisplus.extend.common.utils.PageUtils;
import io.dreamtale.mybatisplus.extend.common.utils.Query;
import io.dreamtale.modules.sys.form.PasswordForm;
import io.dreamtale.service.sys.SysUserRoleService;
import io.dreamtale.service.sys.SysUserService;
import io.dreamtale.utils.R;
import io.dreamtale.validator.Assert;
import io.dreamtale.validator.ValidatorUtils;
import io.dreamtale.validator.group.AddGroup;
import io.dreamtale.validator.group.UpdateGroup;

import org.apache.commons.lang.ArrayUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 系统用户
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年10月31日 上午10:40:10
 */
@RestController
@RequestMapping("/sys/user")
public class SysUserController extends AbstractController {
	@Reference(version = "1.0.0")
	private SysUserService sysUserService;
	@Reference(version = "1.0.0")
	private SysUserRoleService sysUserRoleService;


	/**
	 * 所有用户列表
	 */
	@GetMapping("/list")
	@RequiresPermissions("sys:user:list")
	public R list(@RequestParam Map<String, Object> params) {
		//只有超级管理员，才能查看所有管理员列表
		if (getUserId() != Constant.SUPER_ADMIN) {
			params.put("createUserId", getUserId());
		}
		params.put("parentId", getUserId());
		IPage<SysUserEntity> pageable = new Query<SysUserEntity>(params).getPage();

		PageUtils page = sysUserService.queryPage(params, pageable);

		return R.ok().put("page", page);
	}
	/**
	 * 角色列表
	 */
	@GetMapping("/select")
	@RequiresPermissions("sys:role:select")
	public R select(){
		Map<String, Object> map = new HashMap<>();

		//如果不是超级管理员，则只查询自己所拥有的角色列表
		if(getUserId() != Constant.SUPER_ADMIN){
			map.put("create_user_id", getUserId());
		}
		List<SysUserEntity> list = sysUserService.list(map);

		return R.ok().put("list", list);
	}
	/**
	 * 获取登录的用户信息
	 */
	@GetMapping("/info")
	public R info(){
		return R.ok().put("user", getUser());
	}
	
	/**
	 * 修改登录用户密码
	 */
	@SysLog("修改密码")
	@PostMapping("/password")
	public R password(@RequestBody PasswordForm form){
		Assert.isBlank(form.getNewPassword(), "新密码不为能空");
		
		//sha256加密
		String password = new Sha256Hash(form.getPassword(), getUser().getSalt()).toHex();
		//sha256加密
		String newPassword = new Sha256Hash(form.getNewPassword(), getUser().getSalt()).toHex();
				
		//更新密码
		boolean flag = sysUserService.updatePassword(getUserId(), password, newPassword);
		if(!flag){
			return R.error("原密码不正确");
		}
		
		return R.ok();
	}
	
	/**
	 * 用户信息
	 */
	@GetMapping("/info/{userId}")
	@RequiresPermissions("sys:user:info")
	public R info(@PathVariable("userId") Long userId){
		SysUserEntity user = sysUserService.getById(userId);
		
		//获取用户所属的角色列表
		List<Long> roleIdList = sysUserRoleService.queryRoleIdList(userId);
		user.setRoleIdList(roleIdList);
		
		return R.ok().put("user", user);
	}
	
	/**
	 * 保存用户
	 */
	@SysLog("保存用户")
	@PostMapping("/save")
	@RequiresPermissions("sys:user:save")
	public R save(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, AddGroup.class);
		
		user.setCreateUserId(getUserId());
		sysUserService.save(user);
		
		return R.ok();
	}
	
	/**
	 * 修改用户
	 */
	@SysLog("修改用户")
	@PostMapping("/update")
	@RequiresPermissions("sys:user:update")
	public R update(@RequestBody SysUserEntity user){
		ValidatorUtils.validateEntity(user, UpdateGroup.class);

		user.setCreateUserId(getUserId());
		sysUserService.update(user);
		
		return R.ok();
	}
	
	/**
	 * 删除用户
	 */
	@SysLog("删除用户")
	@PostMapping("/delete")
	@RequiresPermissions("sys:user:delete")
	public R delete(@RequestBody Long[] userIds){
		if(ArrayUtils.contains(userIds, 1L)){
			return R.error("系统管理员不能删除");
		}
		
		if(ArrayUtils.contains(userIds, getUserId())){
			return R.error("当前用户不能删除");
		}
		
		sysUserService.deleteBatch(userIds);
		
		return R.ok();
	}
}
