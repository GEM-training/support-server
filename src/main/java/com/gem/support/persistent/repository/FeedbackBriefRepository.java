package com.gem.support.persistent.repository;

import com.mysema.query.Tuple;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vanhop on 3/10/16.
 */
@Repository
public interface FeedbackBriefRepository {

    List<Tuple> findAllFeedbackBrief(Pageable pageable);
    List<Tuple> findAllFeedbackBriefOfCompany(String companyId, Pageable pageable);

}
