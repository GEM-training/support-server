package com.gem.test.service;

import com.gem.support.Application;
import com.gem.support.service.FeedbackBriefService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by vanhop on 3/15/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={Application.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class FeedBackBrief {

    @Autowired
    private FeedbackBriefService feedbackBriefService;

    @Test
    public void queryFeedbackOfCompany(){

        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 20;
            }

            @Override
            public int getOffset() {
                return 0;
            }

            @Override
            public Sort getSort() {
                return null;
            }

            @Override
            public Pageable next() {
                return null;
            }

            @Override
            public Pageable previousOrFirst() {
                return null;
            }

            @Override
            public Pageable first() {
                return null;
            }

            @Override
            public boolean hasPrevious() {
                return false;
            }
        };
        Assert.assertTrue(feedbackBriefService.findAllByCompanyId("test",pageable).getContent().size() < 1);
        Assert.assertTrue(feedbackBriefService.findAllByCompanyId("046b6c7f-0b8a-43b9-b35d-6489e6daee91",pageable).getContent().size() > 0);
    }

}
