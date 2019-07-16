//package io.dreamtale.bean.entity.cacheversion;
//
//import com.bianmaba.quality.Constants;
//import com.bianmaba.spring.data.jpa.domain.annotations.QueryCacheable;
//import com.bianmaba.spring.data.jpa.domain.entity.NumberIdentityEntity;
//
//import org.hibernate.annotations.Cache;
//import org.hibernate.annotations.CacheConcurrencyStrategy;
//import org.hibernate.annotations.DynamicInsert;
//import org.hibernate.annotations.DynamicUpdate;
//
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
// * 资源实体类
// *
// * @author cWX183898
// */
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//@AllArgsConstructor
//@Builder
//@QueryCacheable
//@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
//@Table(name = "cache_version")
//public class CacheVersion extends NumberIdentityEntity {
//    private String name;
//    private String param;
//    private String remark;
//    private String method;
//    private String createTime= Constants.getLocalDateTime();
//}
