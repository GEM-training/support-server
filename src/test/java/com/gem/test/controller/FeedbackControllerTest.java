package com.gem.test.controller;

import com.gem.support.Application;
import com.gem.support.persistent.model.Feedback;
import com.gem.support.persistent.repository.FeedbackRepository;
import com.gem.support.service.FeedbackService;
import com.gem.support.service.exception.ResourceNotFoundException;
import com.gem.support.service.impl.FeedbackServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.runners.MockitoJUnitRunner;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.annotations.BeforeMethod;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
/**
 * Created by vanhop on 3/17/16.
 */
@RunWith(MockitoJUnitRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes={Application.class})
public class FeedbackControllerTest {

    @Mock
    private FeedbackRepository feedbackRepository;

    private FeedbackService feedbackService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        feedbackService = new FeedbackServiceImpl(feedbackRepository);
        when(feedbackRepository.findOne(any(String.class))).thenReturn(any(Feedback.class));
    }

    @Test
    public void findOne(){
       // when(feedbackRepository.findOne(any(String.class))).thenThrow(new ResourceNotFoundException("TEST"));
        feedbackService.findOne("ff808181537811c3015378127bc60000");
        verify(feedbackRepository).findOne(any(String.class));
    }





}
