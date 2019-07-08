package io.renren.modules.app.service.impl;


import io.renren.common.base.service.BaseService;
import io.renren.common.exception.RRException;
import io.renren.common.validator.Assert;
import io.renren.modules.app.dao.UserDao;
import io.renren.modules.app.entity.UserEntity;
import io.renren.modules.app.form.LoginForm;
import io.renren.modules.app.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service("userService")
public class UserServiceImpl extends BaseService<UserDao, UserEntity> implements UserService {

	@Override
	public UserEntity queryByMobile(String mobile) {
		Map<String,Object> params = new HashMap<>();
		params.put("mobile",mobile);
		return this.getOne(params);
	}

	@Override
	public long login(LoginForm form) {
		UserEntity user = queryByMobile(form.getMobile());
		Assert.isNull(user, "手机号或密码错误");
		//密码错误
		if(!user.getPassword().equals(DigestUtils.sha256Hex(form.getPassword()))){
			throw new RRException("手机号或密码错误");
		}
		return user.getUserId();
	}
}
