package io.dreamtale.bean.entity.detailsphase;

import com.baomidou.mybatisplus.annotation.TableName;

import io.dreamtale.bean.base.NumberIdentityEntity;
import lombok.Data;

/**
 * @className: Detailsphase
 * @description:设计阶段详情
 * @author: qikan
 * @create: 2018/7/27 15:17
 **/
@Data
@TableName("detailsphase")
public class Detailsphase extends NumberIdentityEntity {
    /**
     * 创建人责任人
     */
    private Long creator;
    /**
     * 责任人
     */
    private Long userId;
    /**
     * 项目
     */
    private Long projectId;
//    /**
//     * 项目
//     */
//    @ManyToOne()
//    @JoinColumn(name = "term")
//    private Term term;
    /**
     * 阶段
     */
    private Long designphaseId;
    /**
     * 阶段状态
     */
    private Long designphaseStateId;
    /**
     * 计划开始时间
     */
    private String planStartDate;
    /**
     * 计划结束时间
     */
    private String planEndDate;

    /**
     * 调整时间
     */
//    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinColumn(name = "detailsphase_id")
//    @Fetch(FetchMode.SUBSELECT)
//    private List<AdjustTime> adjustTimes = new ArrayList<>();
    /**
     * 实际完成时间
     */
    private String actualcompletionTime;
    /**
     * 备注
     */
    private String remark;
    /**
     * 完成备注
     */
    private String completRemark;

    private Integer operateState = 0;

    /**
     * 会议
     */
//    @OneToMany(cascade = CascadeType.ALL)
//    @JoinColumn(name = "detailsphase_id")
//    private List<Meeting> meetings = new ArrayList<>();

    private Integer terms;
}
