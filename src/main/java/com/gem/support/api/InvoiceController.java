package com.gem.support.api;


import com.gem.support.service.InvoiceService;
import com.gem.support.service.dto.InvoiceDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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

    @RequestMapping(value = "", params = "download", method = RequestMethod.GET)
    public ResponseEntity<ByteArrayResource> downloadExcel(
            @RequestParam(name = "companyId", required = false) String companyId,
            @RequestParam(name = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
            @RequestParam(name = "download") String download) {
        if(download.equals("excel")) {
            byte[] excelBytes = invoiceService.exportExcel(companyId, from, to);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
            headers.add("Pragma", "no-cache");
            headers.add("Expires", "0");
            headers.add("Content-disposition","attachment;filename=invoices.xlsx");
            return ResponseEntity.ok()
                    .headers(headers)
                    .contentLength(excelBytes.length)
                    .contentType(MediaType.parseMediaType("application/octet-stream"))
                    .body(new ByteArrayResource(excelBytes));
        }
        return null;
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
