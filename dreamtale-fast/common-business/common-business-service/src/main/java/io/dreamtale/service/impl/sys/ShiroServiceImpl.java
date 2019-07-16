package io.dreamtale.service.impl.sys;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import io.dreamtale.bean.entity.sys.entity.SysMenuEntity;
import io.dreamtale.bean.entity.sys.entity.SysUserEntity;
import io.dreamtale.bean.entity.sys.entity.SysUserTokenEntity;
import io.dreamtale.mybatisplus.extend.common.utils.Constant;
import io.dreamtale.service.sys.ShiroService;
import io.dreamtale.service.sys.SysMenuService;
import io.dreamtale.service.sys.SysUserService;
import io.dreamtale.service.sys.SysUserTokenService;

@Service
public class ShiroServiceImpl implements ShiroService {
    @Autowired
    private SysMenuService sysMenuService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private SysUserTokenService sysUserTokenService;

    @Override
    public Set<String> getUserPermissions(long userId) {
        List<String> permsList;

        //系统管理员，拥有最高权限
        if (userId == Constant.SUPER_ADMIN) {
            List<SysMenuEntity> menuList = sysMenuService.list(null);
            permsList = new ArrayList<>(menuList.size());
            for (SysMenuEntity menu : menuList) {
                permsList.add(menu.getPerms());
            }
        } else {
            permsList = sysUserService.queryAllPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public SysUserTokenEntity queryByToken(String token) {
        return sysUserTokenService.queryByToken(token);
    }

    @Override
    public SysUserEntity queryUser(Long userId) {
        return sysUserService.getById(userId);
    }
}
