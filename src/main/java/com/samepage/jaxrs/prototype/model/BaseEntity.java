package com.samepage.jaxrs.prototype.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
@MappedSuperclass
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class BaseEntity implements Serializable {

    @Transient
    public static final long serialVersionUID = 1L;

    @Version
    protected long version;

    @XmlElement
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;


    @Temporal(TemporalType.TIMESTAMP)
    protected Date createDateTime;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date modifyDateTime;

    public long getVersion() {
        return version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDateTime() {
        return createDateTime;
    }


    public Date getModifyDateTime() {
        return modifyDateTime;
    }

    @PreUpdate
    @PrePersist
    public void updateTimeStamps() {
        modifyDateTime = new Date();
        if(createDateTime == null) {
            createDateTime = new Date(modifyDateTime.getTime());
        }
    }
}
