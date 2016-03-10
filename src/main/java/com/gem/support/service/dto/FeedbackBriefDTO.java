package com.gem.support.service.dto;

import java.util.Date;

/**
 * Created by vanhop on 3/10/16.
 */
public class FeedbackBriefDTO {

    private String id;
    private String userId;
    private String username;
    private String companyId;
    private String companyName;
    private String subContent;
    private String avatar;
    private Date time;

    public FeedbackBriefDTO() {
    }

    public FeedbackBriefDTO(String id, String userId, String username, String companyId, String companyName, String subContent, String avatar, Date time) {
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
