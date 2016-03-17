package com.gem.support.persistent.model;

/**
 * Created by VanHop on 3/8/2016.
 */
public class CompanyFeedback {

    private String uuid;
    private String companyName;
    private long numOfTicket;

    public CompanyFeedback() {
    }

    public CompanyFeedback(String companyName, long numOfTicket) {
        this.companyName = companyName;
        this.numOfTicket = numOfTicket;
    }

    public CompanyFeedback(String uuid, String companyName, long numOfTicket) {
        this.uuid = uuid;
        this.companyName = companyName;
        this.numOfTicket = numOfTicket;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getNumOfTicket() {
        return numOfTicket;
    }

    public void setNumOfTicket(long numOfTicket) {
        this.numOfTicket = numOfTicket;
    }
}
