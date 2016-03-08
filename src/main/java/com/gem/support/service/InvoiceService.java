package com.gem.support.service;

import com.gem.support.service.dto.InvoiceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface InvoiceService extends BaseService<InvoiceDTO, String> {

    Page<InvoiceDTO> find(String companyId, Date from, Date to, Pageable pageable);

}
