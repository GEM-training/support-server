package com.gem.support.persistent.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by vanhop on 3/8/16.
 */
@Entity
@Table(name = "user_company")
public class UserCompany {



    @Id
    @Column(name = "user_id", unique = true)
    private String userId;

    @Column(name = "username")
    private String username;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "company_id")
    private String companyId;

    @Column(name = "company_name")
    private String companyName;

    public UserCompany() {
    }

    public UserCompany(String userId, String username, String avatar, String companyId, String companyName) {
        this.userId = userId;
        this.username = username;
        this.avatar = avatar;
        this.companyId = companyId;
        this.companyName = companyName;
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

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
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
