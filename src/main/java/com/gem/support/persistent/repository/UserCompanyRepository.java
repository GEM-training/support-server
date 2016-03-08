package com.gem.support.persistent.repository;

import com.gem.support.persistent.model.UserCompany;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by vanhop on 3/8/16.
 */
@Repository
public interface UserCompanyRepository extends PagingAndSortingRepository<UserCompany,String > {
    UserCompany findByUserId(String userId);
}
