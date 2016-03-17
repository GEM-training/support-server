package com.gem.support.service.impl;

import com.gem.support.persistent.model.QFeedback;
import com.gem.support.persistent.model.QUserCompany;
import com.gem.support.persistent.repository.FeedbackBriefRepository;
import com.gem.support.service.FeedbackBriefService;
import com.gem.support.service.dto.FeedbackBriefDTO;
import com.mysema.query.Tuple;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vanhop on 3/10/16.
 */

@Service
@Transactional
public class FeedbackBriefServiceImpl implements FeedbackBriefService {

    @Autowired
    private FeedbackBriefRepository feedbackBriefRepository;

    @Override
    public Page<FeedbackBriefDTO> findAll(Pageable pageable) {
        return feedbackBriefRepository.findAll(pageable).map(source ->{
            FeedbackBriefDTO feedbackBriefDTO = new FeedbackBriefDTO();
            BeanUtils.copyProperties(source, feedbackBriefDTO);
            return  feedbackBriefDTO;
        });
    }

    @Override
    public Page<FeedbackBriefDTO> findAllByCompanyId(String companyId, Pageable pageable) {
        return feedbackBriefRepository.findByCompanyIdOrderByTimeDesc(companyId, pageable).map(source -> {
            FeedbackBriefDTO feedbackBriefDTO = new FeedbackBriefDTO();
            BeanUtils.copyProperties(source, feedbackBriefDTO);
            return feedbackBriefDTO;
        });
    }
}
