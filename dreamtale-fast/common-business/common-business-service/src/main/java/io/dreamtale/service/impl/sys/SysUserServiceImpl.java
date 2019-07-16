package io.dreamtale.service.impl.sys;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.dreamtale.bean.entity.sys.entity.SysUserEntity;
import io.dreamtale.dao.sys.dao.SysUserDao;
import io.dreamtale.exception.RRException;
import io.dreamtale.mybatisplus.extend.common.base.service.BaseService;
import io.dreamtale.mybatisplus.extend.common.utils.Constant;
import io.dreamtale.mybatisplus.extend.common.utils.PageUtils;
import io.dreamtale.service.sys.SysRoleService;
import io.dreamtale.service.sys.SysUserRoleService;
import io.dreamtale.service.sys.SysUserService;


/**
 * 系统用户
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年9月18日 上午9:46:09
 */
@Service("sysUserService")
public class SysUserServiceImpl extends BaseService<SysUserDao, SysUserEntity> implements SysUserService {
    @Autowired
    private SysUserRoleService sysUserRoleService;
    @Autowired
    private SysRoleService sysRoleService;

    @Override
    public PageUtils queryPage(Map<String, Object> params, IPage<SysUserEntity> pageable) {
        Long parentId = (Long) params.get("parentId");
        List<SysUserEntity> allChildrenUsers = baseMapper.queryAllChildrenUser(parentId);
        List<Long> ids = new ArrayList<>();
        ids = getIds(allChildrenUsers, ids);
        params.put("userId_in", ids.toArray());
        IPage<SysUserEntity> page = this.page(
            pageable,
            params
        );
        return new PageUtils(page);
    }

    List<Long> getIds(List<SysUserEntity> allChildrenUsers, List<Long> ids) {
        if (!allChildrenUsers.isEmpty()) {
            for (SysUserEntity sysUserEntity :
                allChildrenUsers) {
                ids.add(sysUserEntity.getUserId());
                getIds(sysUserEntity.getChildren(), ids);
            }
        }
        return ids;
    }

    @Override
    public List<String> queryAllPerms(Long userId) {
        return baseMapper.queryAllPerms(userId);
    }

    @Override
    public List<Long> queryAllMenuId(Long userId) {
        return baseMapper.queryAllMenuId(userId);
    }

    @Override
    public SysUserEntity queryByUserName(String username) {
        return baseMapper.queryByUserName(username);
    }

    @Override
    @Transactional
    public boolean save(SysUserEntity user) {
        user.setCreateTime(new Date());
        //sha256加密
        String salt = RandomStringUtils.randomAlphanumeric(20);
        user.setPassword(new Sha256Hash(user.getPassword(), salt).toHex());
        user.setSalt(salt);
        this.save(user);
        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
        return false;
    }

    @Override
    @Transactional
    public void update(SysUserEntity user) {
        if (StringUtils.isBlank(user.getPassword())) {
            user.setPassword(null);
        } else {
            user.setPassword(new Sha256Hash(user.getPassword(), user.getSalt()).toHex());
        }
        this.updateById(user);

        //检查角色是否越权
        checkRole(user);

        //保存用户与角色关系
        sysUserRoleService.saveOrUpdate(user.getUserId(), user.getRoleIdList());
    }

    @Override
    public void deleteBatch(Long[] userId) {
        this.removeByIds(Arrays.asList(userId));
    }

    @Override
    public boolean updatePassword(Long userId, String password, String newPassword) {
        SysUserEntity userEntity = new SysUserEntity();
        userEntity.setPassword(newPassword);
        return this.getBaseMapper().update(userEntity,
            new QueryWrapper<SysUserEntity>().eq("user_id", userId).eq("password", password))>0;
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(SysUserEntity user) {
        if (user.getRoleIdList() == null || user.getRoleIdList().size() == 0) {
            return;
        }
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if (user.getCreateUserId() == Constant.SUPER_ADMIN) {
            return;
        }

        //查询用户创建的角色列表
        List<Long> roleIdList = sysRoleService.queryRoleIdList(user.getCreateUserId());

        //判断是否越权
        if (!roleIdList.containsAll(user.getRoleIdList())) {
            throw new RRException("新增用户所选角色，不是本人创建");
        }
    }
}
