//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.dreamtale.bean.base;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Inheritance(
    strategy = InheritanceType.TABLE_PER_CLASS
)
public abstract class UuidIdentityEntity implements IEntity {
    @Id
    @TableId(type = IdType.UUID)
    @Column(
        length = 36,
        nullable = false
    )
    protected String id;

    public UuidIdentityEntity() {
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
