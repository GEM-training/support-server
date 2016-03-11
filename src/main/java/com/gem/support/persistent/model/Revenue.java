package com.gem.support.persistent.model;

import java.util.Date;

/**
 * This class is a data container for total revenue in a time period.
 * This is not a model class.
 */
public class Revenue {
    private Date from;
    private Date to;
    private long totalUser;
    private int userIncrement;
    private double totalRevenue;

    public Revenue() {
    }

    public Revenue(Date from, Date to, long totalUser, int userIncrement, double totalRevenue) {
        this.from = from;
        this.to = to;
        this.totalUser = totalUser;
        this.userIncrement = userIncrement;
        this.totalRevenue = totalRevenue;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public long getTotalUser() {
        return totalUser;
    }

    public void setTotalUser(long totalUser) {
        this.totalUser = totalUser;
    }

    public int getUserIncrement() {
        return userIncrement;
    }

    public void setUserIncrement(int userIncrement) {
        this.userIncrement = userIncrement;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
