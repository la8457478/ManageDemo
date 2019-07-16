//package io.dreamtale.bean.entity.fieldservice;
//
//import com.bianmaba.spring.data.jpa.domain.entity.NumberIdentityEntity;
//
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Table;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
///**
// * 现场服务公司统计历史记录
// */
//@DynamicUpdate(true)
//@DynamicInsert(true)
//@Entity
//@Getter
//@Setter
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//@Table(name = "field_service_user_statistics_record")
//public class FieldServiceUserStatisticsRecord extends NumberIdentityEntity {
//    /**
//     * id
//     */
//    @Column(length = 64)
//    private String user;
//    /**
//     * 公司id
//     */
//    @Column(length = 64)
//    private String company;
//    /**
//     * 用户名
//     */
//    @Column(length = 64)
//    private String userName;
//    /**
//     * 用户角色
//     */
//    @Column(length = 64)
//    private String role;
//    /**
//     * 现场服务专业
//     */
//    @Column(length = 64)
//    private String major;
//    /**
//     * 服务次数
//     */
//    @Column(length = 11, columnDefinition = "INT default 0")
//    private Integer serviceTime;
//    /**
//     * 月份
//     */
//    @Column(length = 64, nullable = true)
//    private String month;
//
//
//}
