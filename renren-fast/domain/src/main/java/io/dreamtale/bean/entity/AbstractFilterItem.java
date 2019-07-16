package io.dreamtale.bean.entity;

import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

import io.dreamtale.bean.base.NumberIdentityEntity;
import io.dreamtale.bean.base.UuidIdentityEntity;

/**
 * @program: realty
 * @description:地区抽象类
 * @author: Chenjiabin
 * @create: 2018-04-23 21:26
 **/

@MappedSuperclass
@Inheritance(
        strategy = InheritanceType.TABLE_PER_CLASS
)
public abstract class AbstractFilterItem extends UuidIdentityEntity {
    @Column(nullable = false, length = 255)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract Map<String, Object> createFilterParams();


}
