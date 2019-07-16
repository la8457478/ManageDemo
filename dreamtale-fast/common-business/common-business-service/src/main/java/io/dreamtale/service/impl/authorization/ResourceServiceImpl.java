package io.dreamtale.service.impl.authorization;


import org.springframework.stereotype.Service;

import io.dreamtale.bean.entity.authorization.Resource;
import io.dreamtale.dao.authorization.resource.ResourceDao;
import io.dreamtale.mybatisplus.extend.common.base.service.BaseService;
import io.dreamtale.service.authorization.ResourceService;

@Service
public class ResourceServiceImpl  extends BaseService<ResourceDao, Resource> implements ResourceService {
}
