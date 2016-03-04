package com.gem.support.persistent.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "subscription_type")
public class SubscriptionType {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GenericGenerator(name = "guid_generator", strategy = "uuid2")
    @GeneratedValue(generator = "guid_generator")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "period")
    private int period = 1;

    @Column(name = "fee_per_user", nullable = false)
    private double feePerUser = 0;

    @OneToMany(mappedBy = "subscriptionType")
    private Set<Subscription> subscriptions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }

    public double getFeePerUser() {
        return feePerUser;
    }

    public void setFeePerUser(double feePerUser) {
        this.feePerUser = feePerUser;
    }

    public Set<Subscription> getSubscriptions() {
        return subscriptions;
    }

    public void setSubscriptions(Set<Subscription> subscriptions) {
        this.subscriptions = subscriptions;
    }
}
