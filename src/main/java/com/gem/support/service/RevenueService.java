package com.gem.support.service;

import com.gem.support.service.dto.RevenueDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface RevenueService {
    RevenueDTO calculateSumRevenue(String companyId, Date from, Date to);
    Page<RevenueDTO> listSystemRevenueByMonth(Pageable pageable);
}
