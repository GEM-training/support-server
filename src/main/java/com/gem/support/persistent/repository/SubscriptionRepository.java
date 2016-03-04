package com.gem.support.persistent.repository;


import com.gem.support.persistent.model.Subscription;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface SubscriptionRepository extends PagingAndSortingRepository<Subscription, String>, QueryDslPredicateExecutor<Subscription> {
}
