package io.dreamtale.bean.entity.authorization;


import com.baomidou.mybatisplus.annotation.TableName;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import io.dreamtale.bean.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * 资源实体类
 *
 * @author cWX183898
 */
@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
@TableName("company")
public class Company extends BaseEntity {
    private static final long serialVersionUID = -4944044646898487415L;

    /**
     * 名称
     */
    @Column(length = 512, nullable = false)
    private String name;
    /**
     * 省份
     */
    @Column(nullable = false, length = 32)
    private String province;
    /**
     * 城市
     */
    @Column(nullable = false, length = 32)
    private String city;
    /**
     * 行政区域  不用字段是否删除
     */
    @Column(nullable = false, length = 32)
    private String district ="";
    /**
     * 公司类型
     */
    @Column(length = 32)
    private String type;

    private Long parentId;
//    /**
//     * 子公司
//     */
//    @OneToMany(cascade = CascadeType.MERGE, orphanRemoval = true)
//    @JoinColumn(name = "parent_id")
//    @OrderBy("name desc")
//    private List<Company> children = new ArrayList<Company>(0);

}
