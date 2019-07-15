package io.renren.modules.sys.service;


import io.dreamtale.mybatisplus.extend.common.base.service.IBaseService;
import io.dreamtale.mybatisplus.extend.common.utils.R;
import io.renren.modules.sys.entity.SysUserTokenEntity;

/**
 * 用户Token
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:22:07
 */
public interface SysUserTokenService extends IBaseService<SysUserTokenEntity> {

	/**
	 * 生成token
	 * @param userId  用户ID
	 */
	R createToken(long userId);

	/**
	 * 退出，修改token值
	 * @param userId  用户ID
	 */
	void logout(long userId);
	SysUserTokenEntity queryByToken(String token);
}
