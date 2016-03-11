package com.gem.support.persistent.model;

import java.util.Date;

/**
 * This class is a data container for total revenue in a time period.
 * This is not a model class.
 */
public class Revenue {
    private Date from;
    private Date to;
    private double totalRevenue;

    public Revenue(Date from, Date to, double totalRevenue) {
        this.from = from;
        this.to = to;
        this.totalRevenue = totalRevenue;
    }

    public Revenue() {
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

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
