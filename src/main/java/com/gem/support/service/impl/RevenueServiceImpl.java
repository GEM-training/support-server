package com.gem.support.service.impl;

import com.gem.support.persistent.model.Invoice;
import com.gem.support.persistent.model.QInvoice;
import com.gem.support.persistent.repository.InvoiceRepository;
import com.gem.support.persistent.repository.TotalRevenueRepository;
import com.gem.support.service.RevenueService;
import com.gem.support.service.dto.RevenueDTO;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class RevenueServiceImpl implements RevenueService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private TotalRevenueRepository totalRevenueRepository;

    @Override
    public RevenueDTO calculateSumRevenue(String companyId, Date from, Date to) {
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
        int totalUser = 0;
        for(Invoice invoice: invoices) {
            sumRevenue += invoice.getFeePerUser() * invoice.getNumOfUser();
            totalUser += invoice.getNumOfUser();
        }

        RevenueDTO dto = new RevenueDTO();
        dto.setTotalRevenue(sumRevenue);
        dto.setUserIncrement(totalUser);
        dto.setNumOfUser(totalUser);
        dto.setCompanyId(companyId);
        return dto;
    }

    @Override
    public Page<RevenueDTO> listSystemRevenueByMonth(Pageable pageable) {
        return totalRevenueRepository.findAll(pageable).map(source -> {
            RevenueDTO revenue = new RevenueDTO();
            BeanUtils.copyProperties(source, revenue);
            return revenue;
        });
    }
}
