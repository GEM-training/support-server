package com.gem.test.service;


import com.gem.support.persistent.model.Invoice;
import com.gem.support.persistent.repository.InvoiceRepository;
import com.gem.support.service.dto.InvoiceDTO;
import com.gem.support.service.exception.ResourceNotFoundException;
import com.gem.support.service.impl.InvoiceServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)

public class InvoiceServiceTest {
    @InjectMocks
    InvoiceServiceImpl invoiceService;

    @Mock
    InvoiceRepository invoiceRepository;

    @Before
    public void setUp() {
        Invoice invoice = new Invoice();

        Mockito.when(invoiceRepository.findOne("aaaa")).thenReturn(null);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void testFindOne() {
        InvoiceDTO invoiceDTO = invoiceService.findOne("aaaa");
        InvoiceDTO invoiceDTO1 = null;
//        Assert.assertEquals(invoiceDTO, invoiceDTO1);


    }



}
