package com.gem.test.controller;

import com.gem.support.Application;
import com.gem.support.api.FeedbackController;
import com.gem.support.persistent.model.Feedback;
import com.gem.support.persistent.repository.FeedbackRepository;
import com.gem.support.service.FeedbackService;
import com.gem.support.service.dto.CompanyFeedbackDTO;
import com.gem.support.service.dto.FeedbackDTO;
import com.gem.support.service.exception.ResourceInvalidedException;
import com.gem.support.service.exception.ResourceNotFoundException;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.powermock.api.mockito.PowerMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import static org.mockito.Matchers.any;
/**
 * Created by vanhop on 3/17/16.
 */
@RunWith(value=SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={Application.class})
public class FeedbackControllerTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    @InjectMocks
    @Autowired
    private FeedbackService feedbackService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test(expected = ResourceNotFoundException.class)
    public void findOne(){
        when(feedbackRepository.findOne(any(String.class))).then(new Answer<Feedback>() {
            @Override
            public Feedback answer(InvocationOnMock invocationOnMock) throws Throwable {
                return invocationOnMock.getArgumentAt(0,Feedback.class);
            }
        });

        Assert.assertNotNull(feedbackRepository.findOne("ff808181537e703201537efe8e280008"));

    }





}
