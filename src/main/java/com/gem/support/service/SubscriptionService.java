package com.gem.support.service;


import com.gem.support.service.dto.SubscriptionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;

public interface SubscriptionService extends BaseService<SubscriptionDTO, String> {
    Page<SubscriptionDTO> find(Date startDate, Date expirationDate, Pageable pageable);
}
