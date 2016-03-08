package com.gem.support.service;

import com.gem.support.service.dto.RevenueDTO;

import java.util.Date;

/**
 * Created by qsoft on 3/7/16.
 */
public interface RevenueService {
    RevenueDTO getRevenue(String companyId, Date from, Date to);
}
