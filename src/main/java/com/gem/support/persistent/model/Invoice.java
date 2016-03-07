package com.gem.support.persistent.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Model class for table invoice
 */
@Entity(name = "invoice")
public class Invoice {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GenericGenerator(name = "guid_generator", strategy = "uuid2")
    @GeneratedValue(generator = "guid_generator")
    private String id;

    @Column(name = "company_id", nullable = false)
    private String companyId;

    @Column(name = "num_of_user", nullable = false)
    private int numOfUser = 0;

    @Column(name = "fee_per_user", nullable = false)
    private double feePerUser = 0;

    @Column(name = "issued_date", nullable = false)
    private Date issuedDate = new Date();

    @Column(name = "payment_date")
    private Date paymentDate;

    @Column(name = "payment_method")
    private String paymentMethod;

    @Column(name = "payment_instrument")
    private String paymentInstrument;


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

    public String getPaymentInstrument() {
        return paymentInstrument;
    }

    public void setPaymentInstrument(String paymentInstrument) {
        this.paymentInstrument = paymentInstrument;
    }
}
