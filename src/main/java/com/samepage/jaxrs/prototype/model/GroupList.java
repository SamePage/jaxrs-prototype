package com.samepage.jaxrs.prototype.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="groups")
public class GroupList
{
    @XmlElement(name="group")
    private List<Group> groups = new ArrayList<Group>();

    public GroupList() {}

    public GroupList(List<Group> groups) {
        this.groups = groups;
    }

    @XmlTransient
    public List<Group> getGroups() {
        return this.groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

}
