package com.gem.support.service.impl;

import com.gem.support.persistent.model.Feedback;
import com.gem.support.persistent.model.QFeedback;
import com.gem.support.persistent.model.QUserCompany;
import com.gem.support.persistent.model.UserCompany;
import com.gem.support.persistent.repository.FeedbackRepository;
import com.gem.support.persistent.repository.UserCompanyRepository;
import com.gem.support.persistent.repository.UserCompanyRepositoryCustom;
import com.gem.support.service.FeedbackService;
import com.gem.support.service.dto.CompanyFeedbackDTO;
import com.gem.support.service.dto.FeedbackDTO;
import com.gem.support.service.exception.ResourceInvalidedException;
import com.gem.support.service.exception.ResourceNotFoundException;
import com.mysema.query.Tuple;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserCompanyRepositoryCustom userCompanyRepositoryCustom;

    @Autowired
    private UserCompanyRepository userCompanyRepository;

    @Override
    public void create(FeedbackDTO feedbackDTO) {

        try {
            Feedback feedback = new Feedback();
            BeanUtils.copyProperties(feedbackDTO, feedback);
            feedback.setUserId(feedbackDTO.getUserInfo().getUserId());
            //if user is not exist in user_company then insert and otherwise
            if (userCompanyRepository.findByUserId(feedback.getUserId()) == null) {
                FeedbackDTO.UserInfo userInfo = feedbackDTO.getUserInfo();
                userCompanyRepository.save(new UserCompany(userInfo.getUserId(), userInfo.getUsername(), userInfo.getAvatar(), userInfo.getCompanyId(),userInfo.getCompanyName()));
                logger.info(userInfo.getUserId() + " have sent a feedback");
            }
            feedbackRepository.save(feedback);
        } catch (Exception e){
            throw new ResourceInvalidedException(e.getMessage());
        }
    }

    @Override
    public void update(FeedbackDTO feedback) {

    }

    @Override
    public void delete(String feedbackId) {

        if (feedbackRepository.exists(QFeedback.feedback.id.eq(feedbackId))) {
            logger.info(feedbackId + " have been delete");
            feedbackRepository.delete(feedbackId);
        }
        else {
            logger.error(feedbackId + " is not exist . but try to delete");
            throw new ResourceNotFoundException(feedbackId + " is not exist");
        }

    }

    @Override
    public FeedbackDTO findOne(String s) {
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        Feedback feedback = feedbackRepository.findOne(s);
        BeanUtils.copyProperties(feedback, feedbackDTO);
        UserCompany userCompany = userCompanyRepository.findByUserId(feedback.getUserId());
        feedbackDTO.setUserInfo(new FeedbackDTO.UserInfo(userCompany.getUserId(),userCompany.getUsername(),userCompany.getAvatar(),userCompany.getCompanyId(),userCompany.getCompanyName()));

        return feedbackDTO;
    }

    @Override
    public Page<FeedbackDTO> findAll(Pageable pageable) {
        return null;
    }


    @Override
    public List<CompanyFeedbackDTO> getCompanyWithTicket() {

        List<Tuple> companyWithFeedbacks = userCompanyRepositoryCustom.getSumFeedbackOfCompany();
        List<CompanyFeedbackDTO> companyFeedbackDTOs = new ArrayList<>();

        companyWithFeedbacks.stream().forEach(source ->{
                long count = source.get(QUserCompany.userCompany.count()) == null ? 0 : source.get(QUserCompany.userCompany.count());
                companyFeedbackDTOs.add(new CompanyFeedbackDTO(
                        source.get(QUserCompany.userCompany.companyId),
                        source.get(QUserCompany.userCompany.companyName),
                        count));
        });

        return companyFeedbackDTOs;
    }

}
