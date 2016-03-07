package com.gem.support.service.dto;

import java.util.Date;

public class InvoiceDTO {

    private String id;
    private String companyId;
    private int numOfUser;
    private double feePerUser;
    private Date issuedDate;
    private Date paymentDate;
    private String paymentMethod;
    private int userIncrement;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public int getNumOfUser() {
        return numOfUser;
    }

    public void setNumOfUser(int numOfUser) {
        this.numOfUser = numOfUser;
    }

    public double getFeePerUser() {
        return feePerUser;
    }

    public void setFeePerUser(double feePerUser) {
        this.feePerUser = feePerUser;
    }

    public Date getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(Date issuedDate) {
        this.issuedDate = issuedDate;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public int getUserIncrement() {
        return userIncrement;
    }

    public void setUserIncrement(int userIncrement) {
        this.userIncrement = userIncrement;
    }
}
