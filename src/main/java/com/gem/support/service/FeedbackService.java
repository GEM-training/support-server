package com.gem.support.service;

import com.gem.support.service.dto.CompanyFeedbackDTO;
import com.gem.support.service.dto.FeedbackDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.List;

@Service
public interface FeedbackService extends BaseService<FeedbackDTO,String> {

    Page<CompanyFeedbackDTO> getCompanyWithTicket(Pageable pageable);
    Page<FeedbackDTO> findAllByCompanyId(Pageable pageable,String id);
}
