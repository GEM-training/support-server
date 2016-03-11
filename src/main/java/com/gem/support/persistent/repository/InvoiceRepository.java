package com.gem.support.persistent.repository;

import com.gem.support.persistent.model.Invoice;
import com.gem.support.persistent.model.Revenue;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface InvoiceRepository extends
        PagingAndSortingRepository<Invoice, String>, QueryDslPredicateExecutor<Invoice>, InvoiceRepositoryCustom {

    @Query("select new com.gem.support.persistent.model.Revenue(" +
                "min(i.issuedDate), " +
                "max(i.issuedDate), " +
                "sum(i.numOfUser), " +
                "0, " +
                "sum(i.feePerUser * i.numOfUser)) " +
            "from Invoice i " +
            "where i.issuedDate >= ?1 and i.issuedDate <= ?2 " +
            "group by year(i.issuedDate), month(i.issuedDate)")
    Page<Revenue> getTotalRevenue(Date from, Date to, Pageable pageable);

}
