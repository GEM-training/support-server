package com.gem.support.api;


import com.gem.support.service.InvoiceService;
import com.gem.support.service.dto.InvoiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("/billing/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public Page<InvoiceDTO> findByTime(
            @RequestParam(name = "companyId", required = false) String companyId,
            @RequestParam(name = "from", required = false) @DateTimeFormat(pattern = "yyyy-mm") Date from,
            @RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "yyyy-mm") Date to,
            Pageable pageable) {
        return invoiceService.find(companyId,from, to, pageable);
    }



}
