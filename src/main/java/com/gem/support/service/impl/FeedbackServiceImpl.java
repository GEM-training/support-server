package com.gem.support.service.impl;

import com.gem.support.persistent.model.Feedback;
import com.gem.support.persistent.model.QFeedback;
import com.gem.support.persistent.model.QUserCompany;
import com.gem.support.persistent.model.UserCompany;
import com.gem.support.persistent.repository.FeedbackRepository;
import com.gem.support.persistent.repository.UserCompanyRepository;
import com.gem.support.persistent.repository.UserCompanyRepositoryCustom;
import com.gem.support.service.FeedbackService;
import com.gem.support.service.dto.CompanyTicketDTO;
import com.gem.support.service.dto.FeedbackDTO;
import com.mysema.query.Tuple;
import com.mysema.query.jpa.JPASubQuery;
import com.mysema.query.types.Predicate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService {

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Autowired
    private UserCompanyRepositoryCustom userCompanyRepositoryCustom;

    @Autowired
    private UserCompanyRepository userCompanyRepository;

    @Override
    public void create(FeedbackDTO feedbackDTO) {

        Feedback feedback = new Feedback();
        BeanUtils.copyProperties(feedbackDTO, feedback);

        //if user is not exist in user_company then insert and otherwise
        if (userCompanyRepository.findByUserId(feedback.getUserId()) == null)
            userCompanyRepository.save(new UserCompany(feedback.getUserId(), "Van hop", "12345", "Gem2"));
        feedbackRepository.save(feedback);
    }

    @Override
    public void update(FeedbackDTO feedback) {

    }

    @Override
    public void delete(String feedbackId) {

        if (feedbackRepository.exists(QFeedback.feedback.id.eq(feedbackId)))
            feedbackRepository.delete(feedbackId);

    }

    @Override
    public FeedbackDTO findOne(String s) {

        FeedbackDTO feedbackDTO = new FeedbackDTO();
        Feedback feedback = feedbackRepository.findOne(s);
        BeanUtils.copyProperties(feedback, feedbackDTO);
        feedbackDTO.setUserInfo(new FeedbackDTO.UserInfo(feedback.getUserId(), "", "", ""));
        return feedbackDTO;
    }

    @Override
    public Page<FeedbackDTO> findAll(Pageable pageable) {

        PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.DESC,"time");

        return feedbackRepository.findAll(pageRequest).map(source -> {
            FeedbackDTO feedbackDTO = new FeedbackDTO();
            BeanUtils.copyProperties(source, feedbackDTO);
            return feedbackDTO;
        });
    }

    @Override
    public List<CompanyTicketDTO> getCompanyWithTicket() {

        List<Tuple> companyWithTickets = userCompanyRepositoryCustom.getSumFeedbackOfCompany();
        List<CompanyTicketDTO> companyTicketDTOs = new ArrayList<>();

        companyWithTickets.stream().forEach(source ->
                companyTicketDTOs.add(new CompanyTicketDTO(
                        source.get(QUserCompany.userCompany.companyId),
                        source.get(QUserCompany.userCompany.companyName),
                        source.get(QUserCompany.userCompany.count()))));

        return companyTicketDTOs;
    }

    @Override
    public Page<FeedbackDTO> listFeedbackOfCompany(String companyId, Pageable pageable) {

        PageRequest pageRequest = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), Sort.Direction.DESC,"time");

        Predicate predicate = QFeedback.feedback.userId.in(new JPASubQuery().from(QUserCompany.userCompany).where(QUserCompany.userCompany.companyId.eq(companyId)).list(QUserCompany.userCompany.userId));
        return feedbackRepository.findAll(predicate, pageRequest).map(source -> {
            FeedbackDTO feedbackDTO = new FeedbackDTO();
            BeanUtils.copyProperties(source, feedbackDTO);
            feedbackDTO.setUserInfo(new FeedbackDTO.UserInfo(source.getUserId(), "", "", companyId));
            return feedbackDTO;
        });

    }

}
