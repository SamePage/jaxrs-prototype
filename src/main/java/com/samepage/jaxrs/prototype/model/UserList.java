package com.samepage.jaxrs.prototype.model;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name="users")
public class UserList
{
    @XmlElement(name="user")
    private List<User> users = new ArrayList<User>();

    public UserList() {}

    public UserList(List<User> users) {
        this.users = users;
    }

    @XmlTransient
    public List<User> getUsers() {
        return this.users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }


}