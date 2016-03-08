package com.gem.support.persistent.repository;


import com.gem.support.persistent.model.SubscriptionType;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubscriptionTypeRepository extends
        PagingAndSortingRepository<SubscriptionType, String>, QueryDslPredicateExecutor<SubscriptionType> {
}
