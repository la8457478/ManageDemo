package io.dreamtale.service.impl.sys;


import org.springframework.stereotype.Service;

import java.util.Date;

import io.dreamtale.bean.entity.sys.entity.SysUserTokenEntity;
import io.dreamtale.dao.sys.dao.SysUserTokenDao;
import io.dreamtale.mybatisplus.extend.common.base.service.BaseService;
import io.dreamtale.service.sys.SysUserTokenService;
import io.dreamtale.utils.R;
import io.dreamtale.utils.TokenGenerator;


@Service("sysUserTokenService")
public class SysUserTokenServiceImpl extends BaseService<SysUserTokenDao, SysUserTokenEntity> implements SysUserTokenService {
	//12小时后过期
	private final static int EXPIRE = 3600 * 12;


	@Override
	public R createToken(long userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//当前时间
		Date now = new Date();
		//过期时间
		Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

		//判断是否生成过token
		SysUserTokenEntity tokenEntity = this.getById(userId);
		if(tokenEntity == null){
			tokenEntity = new SysUserTokenEntity();
			tokenEntity.setUserId(userId);
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//保存token
			this.save(tokenEntity);
		}else{
			tokenEntity.setToken(token);
			tokenEntity.setUpdateTime(now);
			tokenEntity.setExpireTime(expireTime);

			//更新token
			this.updateById(tokenEntity);
		}

		R r = R.ok().put("token", token).put("expire", EXPIRE);

		return r;
	}

	@Override
	public void logout(long userId) {
		//生成一个token
		String token = TokenGenerator.generateValue();

		//修改token
		SysUserTokenEntity tokenEntity = new SysUserTokenEntity();
		tokenEntity.setUserId(userId);
		tokenEntity.setToken(token);
		this.updateById(tokenEntity);
	}

	@Override
	public SysUserTokenEntity queryByToken(String token) {
		return this.baseMapper.queryByToken(token);
	}
}
