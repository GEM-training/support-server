package com.gem.support.persistent.model;

import org.hibernate.annotations.Subselect;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by VanHop on 3/8/2016.
 */
@Entity
@Subselect("select uc.company_id as company_id, uc.company_name as company_name, count(*) as num_of_ticket from user_company uc inner join feedback f on uc.user_id = f.user_id " +
        "group by uc.company_id, uc.company_name order by uc.company_name asc ")
public class CompanyFeedback {

    @Id
    @Column(name = "company_id")
    private String uuid;
    @Column(name = "company_name")
    private String companyName;
    @Column(name = "num_of_ticket")
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
