package com.gem.support.service.impl;

import com.gem.support.persistent.model.Feedback;
import com.gem.support.persistent.repository.FeedbackRepository;
import com.gem.support.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FeedbackServiceImpl implements FeedbackService{

    @Autowired
    private FeedbackRepository feedbackRepository;

    @Override
    public void create(Feedback feedback) {
        feedbackRepository.save(feedback);
    }

    @Override
    public void update(Feedback feedback) {

    }

    @Override
    public void delete(String s) {

    }

    @Override
    public Feedback findOne(String s) {
        return null;
    }

    @Override
    public Page<Feedback> findAll(Pageable pageable) {
        feedbackRepository.findAll(pageable);
        return null;
    }
}
