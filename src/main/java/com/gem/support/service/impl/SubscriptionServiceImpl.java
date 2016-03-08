package com.gem.support.service.impl;

import com.gem.support.persistent.model.QSubscription;
import com.gem.support.persistent.model.Subscription;
import com.gem.support.persistent.repository.SubscriptionRepository;
import com.gem.support.service.SubscriptionService;
import com.gem.support.service.dto.SubscriptionDTO;
import com.mysema.query.types.expr.BooleanExpression;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
@Transactional
public class SubscriptionServiceImpl implements SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Override
    public void create(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = new Subscription();
        BeanUtils.copyProperties(subscriptionDTO, subscription);
        subscriptionRepository.save(subscription);

    }

    @Override
    public void update(SubscriptionDTO subscriptionDTO) {
        Subscription subscription = subscriptionRepository.findOne(subscriptionDTO.getCompanyId());
        BeanUtils.copyProperties(subscriptionDTO, subscription);
        subscriptionRepository.save(subscription);
    }

    @Override
    public void delete(String id) {
        subscriptionRepository.delete(id);
    }

    @Override
    public SubscriptionDTO findOne(String s) {
        Subscription subscription = subscriptionRepository.findOne(s);
        SubscriptionDTO dto = new SubscriptionDTO();
        BeanUtils.copyProperties(subscription, dto);
        return dto;
    }

    @Override
    public Page<SubscriptionDTO> findAll(Pageable pageable) {
        return subscriptionRepository.findAll(pageable).map(source -> {
            SubscriptionDTO dto = new SubscriptionDTO();
            BeanUtils.copyProperties(source, dto);
            return dto;
        });
    }

    @Override
    public Page<SubscriptionDTO> find(Date startDate, Date expirationDate, Pageable pageable) {
        BooleanExpression predicate = QSubscription.subscription.isNotNull();
        if (startDate != null) {
            predicate = predicate.and(QSubscription.subscription.startDate.goe(startDate));
        }
        if(expirationDate != null) {
            predicate.and(QSubscription.subscription.expirationDate.loe(expirationDate));
        }
        return subscriptionRepository.findAll(predicate, pageable).map(source -> {
            SubscriptionDTO dto = new SubscriptionDTO();
            BeanUtils.copyProperties(source, dto);
            return dto;
        });
    }
}
