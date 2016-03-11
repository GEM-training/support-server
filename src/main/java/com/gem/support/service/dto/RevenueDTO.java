package com.gem.support.service.dto;

import java.util.Date;

public class RevenueDTO {

    private String companyId;
    private Date from;
    private Date to;
    private long numOfUser;
    private int userIncrement;
    private double totalRevenue;

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public long getNumOfUser() {
        return numOfUser;
    }

    public void setNumOfUser(long numOfUser) {
        this.numOfUser = numOfUser;
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

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }
}
