package io.dreamtale.bean.entity.designphase;


import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;

import io.dreamtale.bean.base.NumberIdentityEntity;
import lombok.Data;

/**
 * @className: Designphase
 * @description:设计阶段
 * @author: qikan
 * @create: 2018/7/27 15:13
 **/
@Data
@TableName("designphase")
public class Designphase extends NumberIdentityEntity {

    //设计阶段
    @Column(length = 64)
    private String name;

//    //子设计阶段
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "parent")
//    private List<Designphase> children;
//
//    //设计详情
//    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "designphase")
//    private List<Detailsphase> detailsphases;

    private Long parentId;
    /**
     * 调整时间
     */
    private Boolean planTimeVisible;
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(name = "designphase_ref_meetTemplates", joinColumns = @JoinColumn(name = "designphase_id"), inverseJoinColumns = @JoinColumn(name = "meet_template_id"))
//    List<MeetTemplate> meetTemplates = new ArrayList<>();

    /**
     * 阶段类型 0 = 项目; 1 = 期数
     */
    private Integer type;
    /**
     * 在设计进度主页使用的
      */
//    @Transient
//    private Long stateId;
//    @Transient
//    private List<MeetingVo> meeting= new ArrayList<>();

    public Boolean isTerm(){
        return (type==null||type!=2)?false:true;
    }
}
