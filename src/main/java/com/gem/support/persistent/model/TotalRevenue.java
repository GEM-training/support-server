package com.gem.support.persistent.model;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * This is a fake table for querying total revenue of the whole system.
 * This model has no equivalent table in the database.
 * [CRITICAL WARNING] native sql in use!!! need alternative solution!!!
 */
@Entity
@Table(name = "total_revenue")
@Subselect("select " +
            "row_number() over() as id, " +
            "min(issued_date) as from, " +
            "max(issued_date) as to, " +
            "sum(invoice.num_of_user) as num_of_user, " +
            "0 as user_increment, " +
            "sum(invoice.fee_per_user * invoice.num_of_user) as total_revenue " +
            "from invoice " +
            "group by date_part('year',issued_date), date_part('month',issued_date)")
@Synchronize({"invoice"})
public class TotalRevenue {

    @Id
    @Column(name = "id")
    private Long id;

    @Column(name = "from")
    private Date from;

    @Column(name = "to")
    private Date to;

    @Column(name = "num_of_user")
    private long numOfUser;

    @Column(name = "user_increment")
    private long userIncrement;

    @Column(name = "total_revenue")
    private double totalRevenue;

    public TotalRevenue() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public long getNumOfUser() {
        return numOfUser;
    }

    public void setNumOfUser(long numOfUser) {
        this.numOfUser = numOfUser;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(double totalRevenue) {
        this.totalRevenue = totalRevenue;
    }

    public long getUserIncrement() {
        return userIncrement;
    }

    public void setUserIncrement(long userIncrement) {
        this.userIncrement = userIncrement;
    }
}
