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

import com.google.gson.Gson;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.Map;

import io.dreamtale.bean.entity.sys.entity.SysConfigEntity;
import io.dreamtale.dao.sys.dao.SysConfigDao;
import io.dreamtale.exception.RRException;
import io.dreamtale.mybatisplus.extend.common.base.service.BaseService;
import io.dreamtale.mybatisplus.extend.common.utils.PageUtils;
import io.dreamtale.redis.SysConfigRedis;
import io.dreamtale.service.sys.SysConfigService;

@Service(version = "1.0.0",protocol = "${dubbo.protocol.id}",
	application = "${dubbo.application.id}",registry = "${dubbo.registry.id}",
	timeout = 3000)
@org.springframework.stereotype.Service
public class SysConfigServiceImpl extends BaseService<SysConfigDao, SysConfigEntity> implements SysConfigService {
	@Autowired
	private SysConfigRedis sysConfigRedis;

	@Override
	public PageUtils queryPage(Map<String, Object> params, IPage<SysConfigEntity> pageable) {
		IPage<SysConfigEntity> page = this.page(pageable,params
		);
		return new PageUtils(page);
	}
	
	@Override
	public boolean save(SysConfigEntity config) {
		boolean i = this.save(config);
		sysConfigRedis.saveOrUpdate(config);
		return i ;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void update(SysConfigEntity config) {
		this.update(config);
		sysConfigRedis.saveOrUpdate(config);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void updateValueByKey(String key, String value) {
		baseMapper.updateValueByKey(key, value);
		sysConfigRedis.delete(key);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void deleteBatch(Long[] ids) {
		for(Long id : ids){
			SysConfigEntity config = this.getById(id);
			sysConfigRedis.delete(config.getParamKey());
		}

		this.removeByIds(Arrays.asList(ids));
	}

	@Override
	public String getValue(String key) {
		SysConfigEntity config = sysConfigRedis.get(key);
		if(config == null){
			config = baseMapper.queryByKey(key);
			sysConfigRedis.saveOrUpdate(config);
		}

		return config == null ? null : config.getParamValue();
	}
	
	@Override
	public <T> T getConfigObject(String key, Class<T> clazz) {
		String value = getValue(key);
		if(StringUtils.isNotBlank(value)){
			return new Gson().fromJson(value, clazz);
		}

		try {
			return clazz.newInstance();
		} catch (Exception e) {
			throw new RRException("获取参数失败");
		}
	}
}
