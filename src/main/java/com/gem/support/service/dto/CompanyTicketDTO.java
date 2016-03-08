package com.gem.support.service.dto;

/**
 * Created by VanHop on 3/8/2016.
 */
public class CompanyTicketDTO {

    private String uuid;
    private String companyName;
    private long numOfTicket;

    public CompanyTicketDTO() {
    }

    public CompanyTicketDTO(String companyName, long numOfTicket) {
        this.companyName = companyName;
        this.numOfTicket = numOfTicket;
    }

    public CompanyTicketDTO(String uuid, String companyName, long numOfTicket) {
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
