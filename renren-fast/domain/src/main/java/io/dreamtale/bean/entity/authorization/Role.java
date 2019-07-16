package io.dreamtale.bean.entity.authorization;

import com.baomidou.mybatisplus.annotation.TableName;

import io.dreamtale.bean.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 角色实体类
 *
 * @author cWX183898
 */
@Getter
@Setter
@ToString(exclude = {"children","users","resources"})
@NoArgsConstructor
@TableName("role")
public class Role extends BaseEntity {
    private static final long serialVersionUID = 2343635839703216816L;

    private String description;

//    @ApiModelProperty(value = "app不用")
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "System_Resource_ref_Role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "resource_id"))
//    private List<Resource> resources = new ArrayList<Resource>(0);
//    @ApiModelProperty(value = "app不用")
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "System_User_ref_Role", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
//    private List<User> users = new ArrayList<User>(0);

//    @ApiModelProperty(value = "名称")
//    @Column(length = 64, nullable = false)
    private String name;
    //检查权限
    private boolean checkAccess;
    //处理权限
    private boolean handleAccess;
    //整改合格检验
    private boolean reformAccess;
    //问题查看权限
    private boolean queryAccess;

    /**
     * 管辖角色
     */
//    @ApiModelProperty(value = "管辖角色")
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "parent_id")
//    @OrderBy("name desc")
//    private List<Role> children = new ArrayList<Role>(0);

    /**
     * 集团扣分分值
     */
    private int groupDockScore;
    /**
     *
     *   整改扣分分值
     */
    private int autoDockScore;

}
