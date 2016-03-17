package com.gem.test.controller;

import com.gem.support.Application;
import com.gem.support.service.FeedbackService;
import com.gem.support.service.dto.CompanyFeedbackDTO;
import com.gem.support.service.dto.FeedbackDTO;
import com.gem.support.service.exception.ResourceInvalidedException;
import com.sun.xml.internal.bind.v2.runtime.reflect.Lister;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
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

   /* @Autowired
    private FeedbackService feedbackService;*/

    @Autowired
    private WebApplicationContext wac;
    private MockMvc mockMvc;
    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }
    @Test
    public void getFeedback() throws Exception {
        this.mockMvc.perform(get("/feedback")).andExpect(status().isOk());
    }

/*    @Test(expected = ResourceInvalidedException.class)
    public void createInvalidedFeedback(){
       // when(feedbackService.create(any(FeedbackDTO.class))).thenThrow();
        // create empty feedbackDTO
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackService.create(feedbackDTO);
    }*/

/*    @Test
    public void est(){
        FeedbackService mock = PowerMockito.mock(FeedbackService.class);
        Pageable pageable = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return 2;
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
        PowerMockito.when(mock.getCompanyWithTicket(pageable)).then(new Answer<CompanyFeedbackDTO>() {

            @Override
            public CompanyFeedbackDTO answer(InvocationOnMock invocationOnMock) throws Throwable {
                CompanyFeedbackDTO companyFeedbackDTO = invocationOnMock.getArgumentAt(0,CompanyFeedbackDTO.class);
                return companyFeedbackDTO;
            }
        });

        com.gem.support.api.FeedbackControllerTest employeeController = new com.gem.support.api.FeedbackControllerTest(mock);

        Assert.assertEquals(2, employeeController.companyWithFeedback(pageable).getSize());
    }*/




}
