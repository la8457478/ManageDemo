//package io.dreamtale.bean.entity.detailsphase;
//
//
//import java.utils.ArrayList;
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
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
//@ApiModel(value = "DetailsphaseVo", description = "设计进度详情vo")
//public class DetailsphaseVo {
//    /**
//     * 详情id
//     */
//    @ApiModelProperty(value = "detailsphaseId",example = "detailsphaseId")
//    private Long id;
//
//    /**
//     * 责任人id
//     */
//    @ApiModelProperty(value = "责任人id",example = "责任人id")
//    private String userId;
//    /**
//     * 项目id
//     */
//    @ApiModelProperty(value = "项目id",example = "项目id")
//    private String projectId;
//
//
//    /**
//     * 阶段状态
//     */
//    @ApiModelProperty(value = "状态id",example = "状态id")
//    private Long designphaseStateId;
//
//    /**
//     * 计划开始时间
//     */
//    @ApiModelProperty(value = "计划开始时间",example = "计划开始时间")
//    private String planStartDate;
//    /**
//     * 计划结束时间
//     */
//    @ApiModelProperty(value = "计划结束时间",example = "计划结束时间")
//
//    private String planEndDate;
////    /**
////     * 调整时间
////     */
////    @ApiModelProperty(value = "调整时间",example = "调整时间")
////    private List<AdjustTimeVo> adjustDates=new ArrayList<>();
//
//    /**
//     * 实际完成时间
//     */
//    @ApiModelProperty(value = "实际完成时间",example = "实际完成时间")
//    private String actualcompletionTime;
//    /**
//     * 备注
//     */
//    @ApiModelProperty(value = "备注",example = "备注")
//    private String remark;
//    /**
//     * 完成备注
//     */
//    @ApiModelProperty(value = "完成备注",example = "完成备注")
//    private String completRemark;
//}
