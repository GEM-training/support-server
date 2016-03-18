package com.gem.support.service.impl;

import com.gem.support.persistent.model.*;
import com.gem.support.persistent.repository.CompanyFeecbackRepository;
import com.gem.support.persistent.repository.FeedbackRepository;
import com.gem.support.persistent.repository.UserCompanyRepository;
import com.gem.support.service.FeedbackService;
import com.gem.support.service.dto.CompanyFeedbackDTO;
import com.gem.support.service.dto.FeedbackDTO;
import com.gem.support.service.exception.ResourceInvalidedException;
import com.gem.support.service.exception.ResourceNotFoundException;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import com.mysema.query.jpa.impl.JPAQuery;
import com.mysema.query.types.Predicate;
import com.mysema.query.types.expr.BooleanExpression;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    private static final Logger logger = LoggerFactory.getLogger(FeedbackServiceImpl.class);

    @Autowired
    private SessionFactory sessionFactory;


    public FeedbackServiceImpl() {
    }

    public FeedbackServiceImpl(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserCompanyRepository userCompanyRepository;

    @Autowired
    private CompanyFeecbackRepository companyFeecbackRepository;

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
            }

            if(feedback.getTime() == null)
                feedback.setTime(new Date());

            feedbackRepository.save(feedback);
            logger.info(feedbackDTO.getUserInfo().getUserId() + " have sent a feedback");
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
        if (feedback == null)
            throw new ResourceNotFoundException();
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
    public Page<CompanyFeedbackDTO> getCompanyWithTicket(Pageable pageable) {
        return companyFeecbackRepository.findAll(pageable).map(source -> {
            CompanyFeedbackDTO companyFeedbackDTO = new CompanyFeedbackDTO();
            BeanUtils.copyProperties(source,companyFeedbackDTO);
            return companyFeedbackDTO;
        });
    }

    @Override
    public Page<FeedbackDTO> findAllByCompanyId(Pageable pageable,String id) {
        System.out.println(id);
        HibernateQuery query = new HibernateQuery(sessionFactory.openSession());
        //BooleanExpression predicate = QUserCompany.userCompany.companyId.eq(id);
        List<String> list = query.from(QUserCompany.userCompany).where(QUserCompany.userCompany.companyId.eq(id)).list(QUserCompany.userCompany.userId);

         Predicate predicate = QFeedback.feedback.userId.in(list);
        return feedbackRepository.findAll(predicate, pageable).map(source ->{
            FeedbackDTO feedbackDTO = new FeedbackDTO();
            BeanUtils.copyProperties(source, feedbackDTO);
            UserCompany userCompany = userCompanyRepository.findByUserId(source.getUserId());
            feedbackDTO.setUserInfo(new FeedbackDTO.UserInfo(userCompany.getUserId(),userCompany.getUsername(),userCompany.getAvatar(),userCompany.getCompanyId(),userCompany.getCompanyName()));
            return feedbackDTO;
        });
    }

}
