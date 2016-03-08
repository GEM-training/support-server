package com.gem.support.service.impl;

import com.gem.support.persistent.model.Invoice;
import com.gem.support.persistent.model.QInvoice;
import com.gem.support.persistent.repository.InvoiceRepository;
import com.gem.support.service.InvoiceService;
import com.gem.support.service.dto.InvoiceDTO;
import com.gem.support.service.exception.ResourceNotFoundException;
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
public class InvoiceServiceImpl implements InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public void create(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceDTO, invoice);
        invoice.setIssuedDate(new Date());
        invoiceRepository.save(invoice);
    }

    @Override
    public void update(InvoiceDTO invoiceDTO) {
        if(!invoiceRepository.exists(invoiceDTO.getId()))
            throw new ResourceNotFoundException("requested invoice could not be found");
        Invoice invoice = new Invoice();
        BeanUtils.copyProperties(invoiceDTO, invoice);
        invoiceRepository.save(invoice);
    }

    @Override
    public void delete(String id) {
        if(!invoiceRepository.exists(id))
            throw new ResourceNotFoundException("requested invoice could not be found");
        invoiceRepository.delete(id);
    }

    @Override
    public InvoiceDTO findOne(String s) {
        Invoice invoice = invoiceRepository.findOne(s);
        if(invoice == null)
            throw new ResourceNotFoundException("requested invoice could not be found");
        InvoiceDTO dto = new InvoiceDTO();
        BeanUtils.copyProperties(invoice, dto);
        return dto;
    }

    @Override
    public Page<InvoiceDTO> findAll(Pageable pageable) {
        Page<Invoice> invoices = invoiceRepository.findAll(pageable);
        return invoices.map(source -> {
            InvoiceDTO dto = new InvoiceDTO();
            BeanUtils.copyProperties(source, dto);
            return dto;
        });
    }


    @Override
    public Page<InvoiceDTO> find(String companyId, Date from, Date to, Pageable pageable) {
        BooleanExpression predicate = QInvoice.invoice.isNotNull();
        if(companyId != null) {
            predicate = predicate.and(QInvoice.invoice.companyId.eq(companyId));
        }
        if(from != null) {
            predicate = predicate.and(QInvoice.invoice.issuedDate.goe(from));
        }
        if(to != null) {
            predicate = predicate.and(QInvoice.invoice.issuedDate.loe(to));
        }
        return invoiceRepository.findAll(predicate, pageable).map(source -> {
            InvoiceDTO dto = new InvoiceDTO();
            BeanUtils.copyProperties(source, dto);
            dto.setUserIncrement(invoiceRepository.getUserIncrement(source.getId()));
            return dto;
        });
    }
}
