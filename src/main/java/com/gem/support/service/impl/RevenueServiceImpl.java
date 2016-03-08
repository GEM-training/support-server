package com.gem.support.service.impl;

import com.gem.support.persistent.model.Invoice;
import com.gem.support.persistent.model.QInvoice;
import com.gem.support.persistent.repository.InvoiceRepository;
import com.gem.support.service.RevenueService;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by qsoft on 3/7/16.
 */
@Service
@Transactional
public class RevenueServiceImpl implements RevenueService {
    @Autowired
    InvoiceRepository invoiceRepository;

    @Override
    public Double getRevenue(String companyId, Date from, Date to) {
        BooleanExpression predicate  = QInvoice.invoice.isNotNull();
        if(companyId != null) {
            predicate = predicate.and(QInvoice.invoice.companyId.eq(companyId));
        }
        if(from != null) {
            predicate = predicate.and(QInvoice.invoice.issuedDate.goe(from));
        }
        if(to != null) {
            predicate = predicate.and(QInvoice.invoice.issuedDate.loe(to));
        }

        Iterable <Invoice> invoices = invoiceRepository.findAll(predicate);
        double sumRevenue = 0;
        for(Invoice invoice: invoices) {
            sumRevenue+= invoice.getFeePerUser() * invoice.getNumOfUser();
        }

        return sumRevenue;
    }



}
