package com.gem.support.service.dto;

import com.gem.support.persistent.model.SubscriptionType;

import java.util.Date;

public class SubscriptionDTO {
    private String companyId;
    private SubscriptionType subscriptionType;
    private Date startDate;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public SubscriptionType getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(SubscriptionType subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
}
