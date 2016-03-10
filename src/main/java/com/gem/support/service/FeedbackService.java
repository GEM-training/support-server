package com.gem.support.service;

import com.gem.support.service.dto.CompanyTicketDTO;
import com.gem.support.service.dto.FeedbackDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FeedbackService extends BaseService<FeedbackDTO,String> {
    List<CompanyTicketDTO> getCompanyWithTicket();
}
