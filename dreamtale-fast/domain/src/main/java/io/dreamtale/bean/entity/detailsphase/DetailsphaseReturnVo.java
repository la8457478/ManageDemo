//package io.dreamtale.bean.entity.detailsphase;
//
//import com.bianmaba.quality.bean.entity.meeting.Meeting;
//import com.bianmaba.quality.bean.vo.AdjustTimeVo;
//
//import java.utils.ArrayList;
//import java.utils.List;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
///**
// * @className: Detailsphase
// * @description:设计阶段详情Vo
// * @author: qikan
// * @create: 2018/7/27 15:17
// **/
//@Getter
//@Setter
//@NoArgsConstructor
//@ApiModel(value = "DetailsphaseReturnVo", description = "设计进度详情vo")
//public class DetailsphaseReturnVo {
//
//    protected Long id;
//    /**
//     * 责任人名称
//     */
//    @ApiModelProperty(value = "责任人名称")
//    private String username;
//    /**
//     * 责任人id
//     */
//    @ApiModelProperty(value = "责任人id")
//    private String userId;
//    /**
//     * 创建人id
//     */
//    @ApiModelProperty(value = "创建人id", example = "创建人id")
//    private String creatorId;
//    /**
//     * 项目id
//     */
//    @ApiModelProperty(value = "项目id")
//    private String projectId;
//    /**
//     * 项目名
//     */
//    @ApiModelProperty(value = "项目名")
//    private String projectName;
//    /**
//     * 阶段
//     */
//    @ApiModelProperty(value = "设计阶段id")
//    private Long designphaseId;
//    @ApiModelProperty(value = "设计阶段name")
//    private String designphaseName;
//    /**
//     * 阶段状态
//     */
//    @ApiModelProperty(value = "状态id")
//    private Long designphaseStateId;
//
//    /**
//     * 阶段状态
//     */
//    @ApiModelProperty(value = "用户操作状态id")
//    private Integer operateState;
//    @ApiModelProperty(value = "状态名称")
//    private String designphaseStateName;
//    /**
//     * 计划时间
//     */
//    @ApiModelProperty(value = "计划时间")
//    private String planDate;
//    /**
//     * 调整时间
//     */
//    @ApiModelProperty(value = "调整时间")
//
////    private List<AdjustTimeVo> adjustDate;
//    /**
//     * 实际完成时间
//     */
//    @ApiModelProperty(value = "实际完成时间")
//    private String actualcompletionTime;
//    /**
//     * 备注
//     */
//    @ApiModelProperty(value = "备注")
//    private String remark;
//    /**
//     * 完成备注
//     */
//    @ApiModelProperty(value = "完成备注")
//    private String completRemark;
//    /**
//     * 会议
//     */
//    @ApiModelProperty(value = "完成备注")
//    private List<Meeting> meetings = new ArrayList<>();
//}
