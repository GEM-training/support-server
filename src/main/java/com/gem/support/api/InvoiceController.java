package com.gem.support.api;


import com.gem.support.service.InvoiceService;
import com.gem.support.service.dto.InvoiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/billing/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public Page<InvoiceDTO> find(
            @RequestParam(name = "companyId", required = false) String companyId,
            @RequestParam(name = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
            Pageable pageable) {
        return invoiceService.find(companyId, from, to, pageable);
    }

    @RequestMapping(value = "/{invoiceId}", method = RequestMethod.GET, produces = "application/json")
    public InvoiceDTO findOne(@PathVariable(value = "invoiceId") String invoiceId){
        return invoiceService.findOne(invoiceId);
    }

    @RequestMapping(value = "", method = RequestMethod.POST, consumes = "application/json")
    public void create(@RequestBody InvoiceDTO dto) {
        invoiceService.create(dto);
    }

}
