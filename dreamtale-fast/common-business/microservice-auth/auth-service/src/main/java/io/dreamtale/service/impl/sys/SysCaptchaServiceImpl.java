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

import com.google.code.kaptcha.Producer;

import com.alibaba.dubbo.config.annotation.Service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.image.BufferedImage;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import io.dreamtale.bean.entity.sys.entity.SysCaptchaEntity;
import io.dreamtale.dao.sys.dao.SysCaptchaDao;
import io.dreamtale.exception.RRException;
import io.dreamtale.mybatisplus.extend.common.base.service.BaseService;
import io.dreamtale.mybatisplus.extend.common.utils.DateUtils;
import io.dreamtale.service.sys.SysCaptchaService;

/**
 * 验证码
 *
 * @author Mark sunlightcs@gmail.com
 * @since 2.0.0 2018-02-10
 */
@Service(version = "1.0.0",protocol = "${dubbo.protocol.id}",
    application = "${dubbo.application.id}",registry = "${dubbo.registry.id}",
    timeout = 3000)
@org.springframework.stereotype.Service
public class SysCaptchaServiceImpl extends BaseService<SysCaptchaDao, SysCaptchaEntity> implements SysCaptchaService {
    @Autowired
    private Producer producer;

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if(StringUtils.isBlank(uuid)){
            throw new RRException("uuid不能为空");
        }
        //生成文字验证码
        String code = producer.createText();

        SysCaptchaEntity captchaEntity = new SysCaptchaEntity();
        captchaEntity.setUuid(uuid);
        captchaEntity.setCode(code);
        //5分钟后过期
        captchaEntity.setExpireTime(DateUtils.addDateMinutes(new Date(), 5));
        this.save(captchaEntity);

        return producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {
        Map<String,Object> params = new HashMap<>();
        params.put("uuid",uuid);
        SysCaptchaEntity captchaEntity = this.getOne(params);
        if(captchaEntity == null){
            return false;
        }

        //删除验证码
        this.removeById(uuid);

        if(captchaEntity.getCode().equalsIgnoreCase(code) && captchaEntity.getExpireTime().getTime() >= System.currentTimeMillis()){
            return true;
        }

        return false;
    }
}
