package com.gem.test.service;

import com.gem.support.Application;
import com.gem.support.persistent.repository.InvoiceRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={Application.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)

public class InvoiceTest {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Test
    public void testInvoiceFindOne() {
        assertEquals(14, invoiceRepository.findOne("de305d54-75b4-431b-adb2-eb5b9e546001").getNumOfUser());
        assertEquals("de305d54-75b4-431b-adb2-eb6b9e546011", invoiceRepository.findOne("de305d54-75b4-431b-adb2-eb5b9e546001").getCompanyId());

    }

    @Test
    public void testInvoiceDelete() {
        invoiceRepository.delete("de305d54-75b4-431b-adb2-eb5b9e546041");
    }





}
