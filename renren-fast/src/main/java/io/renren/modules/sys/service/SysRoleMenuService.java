package io.renren.modules.sys.service;


import io.renren.common.base.service.IBaseService;
import io.renren.modules.sys.entity.SysRoleMenuEntity;

import java.util.List;



/**
 * 角色与菜单对应关系
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:42:30
 */
public interface SysRoleMenuService extends IBaseService<SysRoleMenuEntity> {
	
	boolean saveOrUpdate(Long roleId, List<Long> menuIdList);
	
	/**
	 * 根据角色ID，获取菜单ID列表
	 */
	List<Long> queryMenuIdList(Long roleId);

	/**
	 * 根据角色ID数组，批量删除
	 */
	int deleteBatch(Long[] roleIds);
	
}
