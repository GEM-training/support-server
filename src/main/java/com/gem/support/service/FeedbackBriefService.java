package com.gem.support.service;

import com.gem.support.service.dto.FeedbackBriefDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by vanhop on 3/10/16.
 */
@Service
public interface FeedbackBriefService{
    Page<FeedbackBriefDTO> findAll(Pageable pageable);
    Page<FeedbackBriefDTO> findAllByCompanyId(String companyId, Pageable pageable);
}
