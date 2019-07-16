package io.dreamtale.bean.entity.authorization;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import org.apache.commons.lang.time.DateUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;

import io.dreamtale.bean.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 用户实体类
 *
 * @author cWX183898
 */
@Builder
@Data
@TableName("auth_user")
public class AuthUser extends BaseEntity {
    private static final long serialVersionUID = -3289281116728574488L;
    @Column(length = 64)
    private String name;

    @Column(length = 64, nullable = false)
    private String username;

    @Column(length = 255, nullable = false)
    private String password;

    @Column(length = 256)
    private String email;

    @Column(length = 256)
    private String phoneNumber;

    @Column(nullable = false)
    private Boolean locked = false;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date accountExpiringDate = DateUtils.addMonths(new Date(), 6);

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date credentialsExpiringDate = DateUtils.addMonths(new Date(), 6);


    //公司Id
    @JoinColumn(name = "company_id")
    private Long  companyId;
}
