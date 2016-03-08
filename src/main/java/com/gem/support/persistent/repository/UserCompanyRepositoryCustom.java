package com.gem.support.persistent.repository;

import com.mysema.query.Tuple;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by vanhop on 3/8/16.
 */
@Repository
public interface UserCompanyRepositoryCustom {
    List<Tuple> getSumFeedbackOfCompany();
}
