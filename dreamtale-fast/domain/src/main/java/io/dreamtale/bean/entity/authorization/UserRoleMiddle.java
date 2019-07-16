package io.dreamtale.bean.entity.authorization;


import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;

import io.dreamtale.bean.base.NumberIdentityEntity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@TableName("user_ref_role_ref_company")
public class UserRoleMiddle extends NumberIdentityEntity {
    //公司Id
    private Long companyId;
    //角色id
    private Long roleId;
    //ProjectId
//    @ManyToOne(cascade = CascadeType.MERGE)
//    @JoinColumn(name = "project_id")
//    private Project project;
    //userId
    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "user_id")
    private Long userId;
    @Column() //职责
    private String duty;
    @Column() //备注
    private String remark;
//    @Transient
//    private List<UserRoleMiddleVO> others = new ArrayList<UserRoleMiddleVO>();
}
