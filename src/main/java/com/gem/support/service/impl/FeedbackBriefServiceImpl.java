package com.gem.support.service.impl;

import com.gem.support.persistent.model.QFeedback;
import com.gem.support.persistent.model.QUserCompany;
import com.gem.support.persistent.repository.FeedbackBriefRepository;
import com.gem.support.service.FeedbackBriefService;
import com.gem.support.service.dto.FeedbackBriefDTO;
import com.mysema.query.Tuple;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<FeedbackBriefDTO> findAll(Pageable pageable) {
        return convertTuple2FeedbackBrief(feedbackBriefRepository.findAllFeedbackBrief(pageable));
    }

    @Override
    public List<FeedbackBriefDTO> listFeedbackOfCompany(String companyId, Pageable pageable) {
        return convertTuple2FeedbackBrief(feedbackBriefRepository.findAllFeedbackBriefOfCompany(companyId, pageable));
    }

    private List<FeedbackBriefDTO> convertTuple2FeedbackBrief(List<Tuple> tuples) {
        List<FeedbackBriefDTO> feedbackBriefDTOs = new ArrayList<>();
        tuples.forEach(source -> {
            FeedbackBriefDTO feedbackBriefDTO = new FeedbackBriefDTO();
            feedbackBriefDTO.setId(source.get(QFeedback.feedback.id));
            feedbackBriefDTO.setUserId(source.get(QFeedback.feedback.userId));
            feedbackBriefDTO.setUsername(source.get(QUserCompany.userCompany.username));
            feedbackBriefDTO.setCompanyName(source.get(QUserCompany.userCompany.companyName));
            feedbackBriefDTO.setCompanyId(source.get(QUserCompany.userCompany.companyId));
            feedbackBriefDTO.setTime(source.get(QFeedback.feedback.time));
            String content = source.get(QFeedback.feedback.content);
            content = content == null ? "" : content;
            feedbackBriefDTO.setSubContent(content.substring(0, content.length() < 100 ? content.length() : 100));
            feedbackBriefDTOs.add(feedbackBriefDTO);
        });
        return feedbackBriefDTOs;
    }
}
