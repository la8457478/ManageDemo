//package io.dreamtale.bean.entity.fieldservice;
//
//import com.bianmaba.quality.bean.entity.authorization.Company;
//import com.bianmaba.spring.data.jpa.domain.annotations.QueryCacheable;
//import com.bianmaba.spring.data.jpa.domain.entity.NumberIdentityEntity;
//
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
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
//@QueryCacheable
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@Table(name = "field_service_statistics_record")
//public class FieldServiceStatisticsRecord extends NumberIdentityEntity {
//    /**
//     * 公司
//     */
//    @ManyToOne
//    @JoinColumn(name = "company")
//    private Company company;
//    /**
//     * 公司名
//     */
//    @Column(length = 64)
//    private String companyName;
//    /**
//     * 专业类型
//     */
//    @Column(length = 64)
//    private String type;
//    /**
//     * 服务次数
//     */
//    @Column(length = 64)
//    private Double serviceTime;
//    /**
//     * 月份
//     */
//    @Column(length = 64, nullable = true)
//    private String month;
//    /**
//     * 服务总人数
//     */
//    @Column(length = 64, nullable = true)
//    private Integer numberOfPeople;
//
//
//}
