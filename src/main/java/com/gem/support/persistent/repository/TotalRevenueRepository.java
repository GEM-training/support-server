package com.gem.support.persistent.repository;

import com.gem.support.persistent.model.TotalRevenue;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TotalRevenueRepository extends PagingAndSortingRepository<TotalRevenue, Long>,
        QueryDslPredicateExecutor<TotalRevenue>, TotalRevenueRepositoryCustom {

}
