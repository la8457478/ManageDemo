package io.dreamtale.bean.entity.authorization;

import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;

import io.dreamtale.bean.base.BaseEntity;
import lombok.Data;

/**
 * 资源实体类
 *
 * @author cWX183898
 */
@TableName("System_Resource")
@Data
public class Resource extends BaseEntity {
    private static final long serialVersionUID = -4944044646898487415L;

    /**
     * 资源名称
     */
    @Column(length = 512, nullable = false)
    private String name;

    /**
     * 资源URL地址
     */
    @Column(length = 512, nullable = false)
    private String url;

    /**
     * 资源URL描述
     */
    @Column(length = 1024)
    private String description;

//    /**
//     * 资源所属角色列表
//     */
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "System_Resource_ref_Role", joinColumns = @JoinColumn(name = "resource_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private List<Role> roles = new ArrayList<Role>(0);

//
//    public void addRole(Role role) {
//        if (this.getRoles() == null) {
//            this.setRoles(new ArrayList<Role>(0));
//        }
//        this.getRoles().add(role);
//    }


}
