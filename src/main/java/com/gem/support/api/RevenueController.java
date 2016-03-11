package com.gem.support.api;

import com.gem.support.service.RevenueService;
import com.gem.support.service.dto.RevenueDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;


@RestController
@RequestMapping("/billing/revenue")
public class RevenueController  {
    @Autowired
    private  RevenueService revenueService;

    @RequestMapping(value = "/{companyId}", method = RequestMethod.GET, produces = "application/json")
    public RevenueDTO getRevenue(
            @PathVariable("companyId") String companyId,
            @RequestParam(name = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to) {
        return revenueService.getRevenue(companyId, from, to);
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public Page<RevenueDTO> list(
            @RequestParam(name = "from", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date from,
            @RequestParam(name = "to", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date to,
            Pageable pageable) {
        return revenueService.listRevenue(from, to, pageable);
    }

}

