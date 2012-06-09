package com.samepage.jaxrs.prototype.model;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name="group")
@Entity
public class Group extends BaseEntity {

    private String name;

    private String code;

    @XmlTransient
    @ManyToMany(mappedBy = "groups")
    private List<User> users;

    public Group() {
        users = new ArrayList<User>();
    }

    public String getCode() {
        return code;
    }

    public Group(String name, String code) {
        this();
        this.name = name;
        this.code = code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


}
