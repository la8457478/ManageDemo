package io.dreamtale.service.impl.authorization;


import org.springframework.stereotype.Service;

import io.dreamtale.bean.entity.authorization.AuthUser;
import io.dreamtale.dao.authorization.user.AuthUserDao;
import io.dreamtale.mybatisplus.extend.common.base.service.BaseService;
import io.dreamtale.service.authorization.AuthUserService;

@Service
public class AuthUserServiceImpl  extends BaseService<AuthUserDao,  AuthUser> implements AuthUserService {
}
