package com.kaishengit.pojo;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "user", schema = "hibernate", catalog = "")
public class User {
    private int id;
    private String userName;
    private Set<Address> addressSet;
    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Set<Address> getAddressSet() {
        return addressSet;
    }

    public void setAddressSet(Set<Address> addressSet) {
        this.addressSet = addressSet;
    }

}
