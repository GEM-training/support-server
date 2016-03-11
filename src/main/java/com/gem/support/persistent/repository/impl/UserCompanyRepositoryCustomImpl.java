package com.gem.support.persistent.repository.impl;

import com.gem.support.persistent.model.QFeedback;
import com.gem.support.persistent.model.QUserCompany;
import com.gem.support.persistent.repository.UserCompanyRepositoryCustom;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vanhop on 3/8/16.
 */
@Repository
public class UserCompanyRepositoryCustomImpl implements UserCompanyRepositoryCustom {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Tuple> getSumFeedbackOfCompany() {

        HibernateQuery query = new HibernateQuery(sessionFactory.getCurrentSession());
        return query.from(QUserCompany.userCompany)
                    .from(QFeedback.feedback)
                    .where(QUserCompany.userCompany.userId.eq(QFeedback.feedback.userId))
                    .groupBy(QUserCompany.userCompany.companyId, QUserCompany.userCompany.companyName).orderBy(QUserCompany.userCompany.companyName.asc())
                    .list(QUserCompany.userCompany.companyId, QUserCompany.userCompany.companyName, QUserCompany.userCompany.count());
    }

}
