package io.dreamtale.bean.entity.city;

import com.baomidou.mybatisplus.annotation.TableName;
import java.util.HashMap;
import java.util.Map;

import javax.persistence.*;

import io.dreamtale.bean.entity.AbstractFilterItem;
import lombok.Data;

/**
 * @program: realty
 * @description:市
 * @author: Chenjiabin
 * @create: 2018-04-23 21:26
 **/
@TableName("city")
@Data
public class City extends AbstractFilterItem {
    @ManyToOne()
    @JoinColumn(name = "parent_id")
    private String parentId;

    public Map<String, Object> createFilterParams() {
        Map<String, Object> params = new HashMap<>(0);
        params.put("city", getName());
        return params;
    }
}
