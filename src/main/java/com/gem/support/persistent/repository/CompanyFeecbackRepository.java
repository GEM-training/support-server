package com.gem.support.persistent.repository;

import com.gem.support.persistent.model.CompanyFeedback;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vanhop on 3/15/16.
 */
@Repository
public interface CompanyFeecbackRepository extends PagingAndSortingRepository<CompanyFeedback,String> {
}
