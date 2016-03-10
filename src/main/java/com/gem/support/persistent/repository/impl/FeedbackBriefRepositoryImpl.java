package com.gem.support.persistent.repository.impl;

import com.gem.support.persistent.model.QFeedback;
import com.gem.support.persistent.model.QUserCompany;
import com.gem.support.persistent.repository.FeedbackBriefRepository;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vanhop on 3/10/16.
 */
@Repository
public class FeedbackBriefRepositoryImpl implements FeedbackBriefRepository {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Tuple> findAllFeedbackBrief(Pageable pageable) {

        HibernateQuery query = new HibernateQuery(sessionFactory.openSession());

        return query.from(QUserCompany.userCompany)
                .from(QFeedback.feedback)
                .where(QUserCompany.userCompany.userId.eq(QFeedback.feedback.userId))
                .orderBy(QFeedback.feedback.time.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .list(QFeedback.feedback.id,QUserCompany.userCompany.companyName,QUserCompany.userCompany.companyId,QUserCompany.userCompany.username,QFeedback.feedback.userId,QFeedback.feedback.time,QFeedback.feedback.content);

    }

    @Override
    public List<Tuple> findAllFeedbackBriefOfCompany(String companyId, Pageable pageable) {
        HibernateQuery query = new HibernateQuery(sessionFactory.openSession());

        return query.from(QUserCompany.userCompany)
                .from(QFeedback.feedback)
                .where(QUserCompany.userCompany.userId.eq(QFeedback.feedback.userId).and(QUserCompany.userCompany.companyId.eq(companyId)))
                .orderBy(QFeedback.feedback.time.desc())
                .offset(pageable.getOffset()).limit(pageable.getPageSize())
                .list(QFeedback.feedback.id,QUserCompany.userCompany.companyName,QUserCompany.userCompany.companyId,QUserCompany.userCompany.username,QFeedback.feedback.userId,QFeedback.feedback.time,QFeedback.feedback.content);

    }
}
