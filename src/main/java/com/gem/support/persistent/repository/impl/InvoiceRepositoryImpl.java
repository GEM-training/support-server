package com.gem.support.persistent.repository.impl;

import com.gem.support.persistent.model.Invoice;
import com.gem.support.persistent.model.QInvoice;
import com.gem.support.persistent.repository.InvoiceRepositoryCustom;
import com.mysema.query.jpa.JPQLQuery;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Implementations of {@link com.gem.support.persistent.repository.InvoiceRepository}'s custom functions
 */
@Repository
public class InvoiceRepositoryImpl implements InvoiceRepositoryCustom {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int getUserIncrement(String invoiceId) {
        JPQLQuery query1 = new HibernateQuery(sessionFactory.getCurrentSession());
        Invoice invoice1 = query1.from(QInvoice.invoice).where(QInvoice.invoice.id.eq(invoiceId)).uniqueResult(QInvoice.invoice);
        JPQLQuery query2 = new HibernateQuery(sessionFactory.getCurrentSession());
        Invoice invoice2 = query2.from(QInvoice.invoice).where(QInvoice.invoice.issuedDate.before(invoice1.getIssuedDate()))
                .limit(1).uniqueResult(QInvoice.invoice);
        if(invoice2 != null) {
            return invoice1.getNumOfUser() - invoice2.getNumOfUser();
        }
        return invoice1.getNumOfUser();
    }
}
