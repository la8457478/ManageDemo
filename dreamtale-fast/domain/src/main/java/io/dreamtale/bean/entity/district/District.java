package io.dreamtale.bean.entity.district;


import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

import io.dreamtale.bean.entity.AbstractFilterItem;
import lombok.Data;

/**
 * @program: realty
 * @description:区
 * @author: Chenjiabin
 * @create: 2018-04-23 21:26
 **/
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"name", "parent_id"}))
@Data
public class District extends AbstractFilterItem {
    private String parentId;


    @Override
    public Map<String, Object> createFilterParams() {
        Map<String, Object> params = new HashMap<>(0);
        params.put("district", getName());
        return params;
    }
}
