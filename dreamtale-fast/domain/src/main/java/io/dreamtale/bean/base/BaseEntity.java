//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package io.dreamtale.bean.base;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Inheritance(
    strategy = InheritanceType.TABLE_PER_CLASS
)
public abstract class BaseEntity extends NumberIdentityEntity {
    private static final long serialVersionUID = -5841448194726953221L;
    @Column(
        length = 64,
        nullable = false,
        updatable = false
    )
    protected String creator;
    protected Long cdt = (new Date()).getTime();

    public BaseEntity() {
    }

    public Long getCdt() {
        return this.cdt;
    }

    public void setCdt(Long cdt) {
        this.cdt = cdt;
    }

    public String getCreator() {
        return this.creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}
