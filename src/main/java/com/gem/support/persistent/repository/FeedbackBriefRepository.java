package com.gem.support.persistent.repository;

import com.gem.support.persistent.model.FeedbackBrief;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vanhop on 3/15/16.
 */
@Repository
public interface FeedbackBriefRepository extends PagingAndSortingRepository<FeedbackBrief, String> {
    Page<FeedbackBrief> findByCompanyIdOrderByTimeDesc(String compannyId, Pageable pageable);
}
