package io.dreamtale.service.impl.sys;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.dreamtale.bean.entity.sys.entity.SysRoleEntity;
import io.dreamtale.dao.sys.dao.SysRoleDao;
import io.dreamtale.exception.RRException;
import io.dreamtale.mybatisplus.extend.common.base.service.BaseService;
import io.dreamtale.mybatisplus.extend.common.utils.Constant;
import io.dreamtale.mybatisplus.extend.common.utils.PageUtils;
import io.dreamtale.service.sys.SysRoleMenuService;
import io.dreamtale.service.sys.SysRoleService;
import io.dreamtale.service.sys.SysUserRoleService;
import io.dreamtale.service.sys.SysUserService;

/**
 * 角色
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:45:12
 */
@Service(version = "1.0.0",protocol = "${dubbo.protocol.id}",
	application = "${dubbo.application.id}",registry = "${dubbo.registry.id}",
	timeout = 3000)
@org.springframework.stereotype.Service
public class SysRoleServiceImpl extends BaseService<SysRoleDao, SysRoleEntity> implements SysRoleService {
	@Autowired
	private SysRoleMenuService sysRoleMenuService;
	@Autowired
	private SysUserService sysUserService;
    @Autowired
    private SysUserRoleService sysUserRoleService;

	@Override
	public PageUtils queryPage(Map<String, Object> params, IPage<SysRoleEntity> pageable) {
		IPage<SysRoleEntity> page = this.page(
			pageable,
			params
		);
		return new PageUtils(page);
	}

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(SysRoleEntity role) {
        role.setCreateTime(new Date());
        this.save(role);

        //检查权限是否越权
        checkPrems(role);

        //保存角色与菜单关系
        return sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysRoleEntity role) {
        this.update(role);

        //检查权限是否越权
        checkPrems(role);

        //更新角色与菜单关系
        sysRoleMenuService.saveOrUpdate(role.getRoleId(), role.getMenuIdList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] roleIds) {
        //删除角色
        this.removeByIds(Arrays.asList(roleIds));

        //删除角色与菜单关联
        sysRoleMenuService.deleteBatch(roleIds);

        //删除角色与用户关联
        sysUserRoleService.deleteBatch(roleIds);
    }


    @Override
	public List<Long> queryRoleIdList(Long createUserId) {
		return baseMapper.queryRoleIdList(createUserId);
	}

	/**
	 * 检查权限是否越权
	 */
	private void checkPrems(SysRoleEntity role){
		//如果不是超级管理员，则需要判断角色的权限是否超过自己的权限
		if(role.getCreateUserId() == Constant.SUPER_ADMIN){
			return ;
		}
		
		//查询用户所拥有的菜单列表
		List<Long> menuIdList = sysUserService.queryAllMenuId(role.getCreateUserId());
		
		//判断是否越权
		if(!menuIdList.containsAll(role.getMenuIdList())){
			throw new RRException("新增角色的权限，已超出你的权限范围");
		}
	}
}
