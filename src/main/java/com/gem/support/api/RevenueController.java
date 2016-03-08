package com.gem.support.api;

import com.gem.support.service.RevenueService;
import com.gem.support.service.dto.RevenueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by qsoft on 3/7/16.
 */
@RestController
@RequestMapping("/billing/revenue")
public class RevenueController  {
    @Autowired
    private  RevenueService revenueService;
    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public RevenueDTO getRevenue(
            @RequestParam(name = "companyId", required = false) String companyId,
            @RequestParam(name = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
        return revenueService.getRevenue(companyId, from, to);
    }

}

