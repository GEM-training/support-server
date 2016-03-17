package com.gem.support.persistent.model;

import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by vanhop on 3/15/16.
 */
@Entity
@Subselect("select f.id, uc.user_id, uc.username, uc.avatar, uc.company_id, uc.company_name, f.time, substring(f.content for 100) as content from user_company uc inner join feedback f on uc.user_id = f.user_id " +
        "order by time desc")
public class FeedbackBrief {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "username")
    private String username;

    @Column(name = "company_id")
    private String companyId;

    @Column(name = "company_name")
    private String companyName;

    @Column(name = "content")
    private String subContent;

    @Column(name = "avatar")
    private String avatar;

    @Column(name = "time")
    private Date time;

    public FeedbackBrief() {
    }

    public FeedbackBrief(String id, String userId, String username, String companyId, String companyName, String subContent, String avatar, Date time) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.companyId = companyId;
        this.companyName = companyName;
        this.subContent = subContent;
        this.avatar = avatar;
        this.time = time;
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

    public String getSubContent() {
        return subContent;
    }

    public void setSubContent(String subContent) {
        this.subContent = subContent;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

}
