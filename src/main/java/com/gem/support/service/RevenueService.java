package com.gem.support.service;

import java.util.Date;

/**
 * Created by qsoft on 3/7/16.
 */
public interface RevenueService {
    Double getRevenue(String companyId, Date from, Date to);
}
