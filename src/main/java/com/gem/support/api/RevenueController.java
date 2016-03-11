package com.gem.support.api;

import com.gem.support.service.RevenueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequestMapping("/billing/revenue")
public class RevenueController {

    @Autowired
    private  RevenueService revenueService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public Object list(
            @RequestParam(name = "companyId", required = false) String company,
            @RequestParam(name = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
            Pageable pageable) {
        if(company != null) {
            return revenueService.getRevenue(company, from, to);
        }
        return revenueService.listRevenue(from, to, pageable);
    }

}

