package io.dreamtale.bean.entity.fieldservice;


import com.baomidou.mybatisplus.annotation.TableName;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.dreamtale.bean.base.NumberIdentityEntity;
import io.dreamtale.bean.base.constants.Constant;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("field_service")
public class FieldService extends NumberIdentityEntity {
    /**
     * 主题
     */
    @Column(nullable = false, length = 64)
    private String theme;

    /**
     * 现场描述
     */
    @Column(length = 255)
    private String fieldDesc;
//    @ApiModelProperty("图片信息数组，具体访问image.url")
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
//    @JoinColumn(name = "field_service_id")
//    private List<Image> images = new ArrayList<>();
    /**
     * 处理描述
     */
    @Column(length = 255)
    private String handleDesc;
    /**
     * 设计编号
     */
    @Column(length = 128)
    private String designNo;
    /**
     * 服务专业(用户专业)
     */
    @Column(length = 32)
    private String professional;
    /**
     * 服务类型，类型变化则认为现场服务变了，统计有一些逻辑是根据mold的名称来判断的
     */
    @Column(length = 32)
    private String mold;

    /**
     * 创建人
     */
//    @ManyToOne(cascade = CascadeType.MERGE)
//TODO 字段名换了    @JoinColumn(name = "user")
    private Long  useId;

    /**
     * 关联项目
     */
//    @ManyToOne
//    @JoinColumn(name = "project_id")
    private Long projectId;
    @Column(nullable = true)
    private String createTime = Constant.getLocalDateTime();
    @Column(nullable = true)
    private String lastUpdateTime = Constant.getLocalDateTime();
}
