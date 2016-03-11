package com.gem.support.persistent.model;

import org.hibernate.annotations.Subselect;
import org.hibernate.annotations.Synchronize;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

/**
 * This is a fake table for querying total revenue of the whole system.
 * This model has no equivalent table in the database.
 * [CRITICAL WARNING] native sql in use!!! need alternative solution!!!
 */
@Entity
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
    private Long id;

    @Column
    private Date from;

    @Column
    private Date to;

    @Column
    private long numOfUser;

    @Column
    private int userIncrement;

    @Column
    private double totalRevenue;

    public TotalRevenue() {}

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
