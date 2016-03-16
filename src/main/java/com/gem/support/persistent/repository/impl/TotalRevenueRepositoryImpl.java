package com.gem.support.persistent.repository.impl;

import com.gem.support.persistent.model.QTotalRevenue;
import com.gem.support.persistent.model.TotalRevenue;
import com.gem.support.persistent.repository.TotalRevenueRepositoryCustom;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TotalRevenueRepositoryImpl implements TotalRevenueRepositoryCustom {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public long getTotalUserIncrement(Long id) {
        JPQLQuery query1 = new HibernateQuery(sessionFactory.getCurrentSession());
        TotalRevenue tr1 = query1.from(QTotalRevenue.totalRevenue1).where(QTotalRevenue.totalRevenue1.id.eq(id)).uniqueResult(QTotalRevenue.totalRevenue1);
        JPQLQuery query2 = new HibernateQuery(sessionFactory.getCurrentSession());
        TotalRevenue tr2 = query2.from(QTotalRevenue.totalRevenue1)
                .where(QTotalRevenue.totalRevenue1.to.before(tr1.getTo()))
                .orderBy(QTotalRevenue.totalRevenue1.to.desc())
                .limit(1).uniqueResult(QTotalRevenue.totalRevenue1);
        if(tr2 != null) {
            return tr1.getNumOfUser() - tr2.getNumOfUser();
        }
        return tr1.getNumOfUser();
    }

}
