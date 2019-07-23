package io.dreamtale.dao.authorization.user;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.springframework.stereotype.Repository;

import io.dreamtale.bean.entity.authorization.AuthUser;

/**
 * Created by cwx183898 on 2017/8/9.
 */
@Repository
public interface AuthUserDao extends BaseMapper<AuthUser> {
}
