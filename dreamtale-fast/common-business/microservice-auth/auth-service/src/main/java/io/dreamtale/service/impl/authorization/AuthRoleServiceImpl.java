package io.dreamtale.service.impl.authorization;




import org.springframework.stereotype.Service;

import io.dreamtale.bean.entity.authorization.AuthRole;
import io.dreamtale.dao.authorization.role.AuthRoleDao;
import io.dreamtale.mybatisplus.extend.common.base.service.BaseService;
import io.dreamtale.service.authorization.AuthRoleService;

@Service
public class AuthRoleServiceImpl  extends BaseService<AuthRoleDao,AuthRole> implements AuthRoleService{
}
