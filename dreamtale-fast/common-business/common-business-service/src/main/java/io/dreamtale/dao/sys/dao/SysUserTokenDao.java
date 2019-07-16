package io.dreamtale.dao.sys.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import io.dreamtale.bean.entity.sys.entity.SysUserTokenEntity;

/**
 * 系统用户Token
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:07
 */
public interface SysUserTokenDao extends BaseMapper<SysUserTokenEntity> {

    SysUserTokenEntity queryByToken(String token);
	
}
