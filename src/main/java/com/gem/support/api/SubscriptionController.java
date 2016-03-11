package com.gem.support.api;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("billing/subscription")
public class SubscriptionController {

   // @Autowired
/*    private SubscriptionService subscriptionService;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = "application/json")
    public Page<SubscriptionDTO> findAll(
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
            @RequestParam(name = "startDate", required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") Date expirationDate,
            Pageable pageable) {
        return subscriptionService.find(startDate, expirationDate, pageable);
    }*/

}
