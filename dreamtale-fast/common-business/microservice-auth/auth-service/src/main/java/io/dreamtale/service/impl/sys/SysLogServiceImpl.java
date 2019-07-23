/**
 * Copyright 2018 人人开源 http://www.renren.io
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package io.dreamtale.service.impl.sys;


import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.Map;

import io.dreamtale.bean.entity.sys.entity.SysLogEntity;
import io.dreamtale.dao.sys.dao.SysLogDao;
import io.dreamtale.mybatisplus.extend.common.base.service.BaseService;
import io.dreamtale.mybatisplus.extend.common.utils.PageUtils;
import io.dreamtale.service.sys.SysLogService;


@Service(version = "1.0.0",protocol = "${dubbo.protocol.id}",
    application = "${dubbo.application.id}",registry = "${dubbo.registry.id}",
    timeout = 3000)
@org.springframework.stereotype.Service

public class SysLogServiceImpl extends BaseService<SysLogDao, SysLogEntity> implements SysLogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params, IPage<SysLogEntity> pageable) {
        IPage<SysLogEntity> page = this.page(pageable,params
        );
        return new PageUtils(page);
    }
}
