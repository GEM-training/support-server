package com.gem.support.persistent.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by vanhop on 3/8/16.
 */
@Entity
@Table(name = "user_company")
public class UserCompany {


    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;

    @Column(name = "user_id", unique = true)
    private String userId;

    @Column(name = "username")
    private String username;

    @Column(name = "company_id")
    private String companyId;

    @Column(name = "company_name")
    private String companyName;
    public UserCompany() {
    }

    public UserCompany(String userId, String username, String companyId, String companyName) {
        this.userId = userId;
        this.username = username;
        this.companyId = companyId;
        this.companyName = companyName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
