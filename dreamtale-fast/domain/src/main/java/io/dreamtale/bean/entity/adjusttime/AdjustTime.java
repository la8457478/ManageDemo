package io.dreamtale.bean.entity.adjusttime;


import com.baomidou.mybatisplus.annotation.TableName;


import io.dreamtale.bean.base.NumberIdentityEntity;
import lombok.Data;

/**
 * 调整时间
 */
@TableName("sys_user")
@Data
public class AdjustTime extends NumberIdentityEntity {

    private String startTime;
    private String endTime;
    private Integer no;
    private Long detailsphaseId;
    private String remark;
}
