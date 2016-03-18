package com.gem.test.service;

import com.gem.support.persistent.model.Invoice;
import com.gem.support.persistent.repository.InvoiceRepository;
import com.gem.support.service.RevenueService;
import com.gem.support.service.impl.RevenueServiceImpl;
import com.mysema.query.types.Predicate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.HashSet;

@RunWith(MockitoJUnitRunner.class)
public class RevenueServiceTest {


    @InjectMocks
    RevenueServiceImpl revenueService;

    @Mock
    InvoiceRepository invoiceRepository;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        HashSet<Invoice> findAllResult = new HashSet<>();
        for(int i = 0; i < 10; i ++) {
            Invoice invoice = new Invoice();
            invoice.setId("id" + i);
            invoice.setCompanyId("companyId" + i);
            findAllResult.add(invoice);
        }
        Predicate predicate = null;
//        Mockito.when(invoiceRepository.findAll()).thenReturn(findAllResult);
        Mockito.when(invoiceRepository.findAll(predicate)).thenReturn(findAllResult);
    }

    @Test
    public void testCalculateTotalRevenue() {
        RevenueService revenueService = new RevenueServiceImpl();
        Assert.assertEquals(revenueService.calculateSumRevenue("id1", null, null), 0);
    }
}
