package io.dreamtale.service.sys;


import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;
import java.util.Map;

import io.dreamtale.bean.entity.sys.entity.SysRoleEntity;
import io.dreamtale.mybatisplus.extend.common.base.service.IBaseService;
import io.dreamtale.mybatisplus.extend.common.utils.PageUtils;


/**
 * 角色
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:42:52
 */
public interface SysRoleService extends IBaseService<SysRoleEntity> {

	PageUtils queryPage(Map<String, Object> params, IPage<SysRoleEntity> pageable);

	boolean save(SysRoleEntity role);

	void update(SysRoleEntity role);

	void deleteBatch(Long[] roleIds);

	
	/**
	 * 查询用户创建的角色ID列表
	 */
	List<Long> queryRoleIdList(Long createUserId);
}
