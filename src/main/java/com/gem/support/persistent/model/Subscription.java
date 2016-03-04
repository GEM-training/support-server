package com.gem.support.persistent.model;


import javax.persistence.*;
import java.util.Date;

@Entity(name = "subscription")
public class Subscription {

    @Id
    @Column(name = "company_id", nullable = false, unique = true, columnDefinition = "UUID")
    private String companyId;

    @ManyToOne
    @JoinColumn(name = "subscription_type_id", referencedColumnName = "id")
    private SubscriptionType subscriptionType;

    @Column(name = "start_date", nullable = false)
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
