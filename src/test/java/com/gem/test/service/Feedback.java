package com.gem.test.service;

/**
 * Created by vanhop on 3/15/16.
 */

import com.gem.support.Application;
import com.gem.support.service.FeedbackService;
import com.gem.support.service.dto.FeedbackDTO;
import com.gem.support.service.exception.ResourceInvalidedException;
import com.gem.support.service.exception.ResourceNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

/**
 * Created by vanhop on 3/15/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={Application.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class Feedback {

    @Autowired
    FeedbackService feedbackService;

    @Test(expected = ResourceNotFoundException.class)
    public void queryResourceNotFound(){
        //query resource not found in database
        feedbackService.findOne("abcd");
    }
    @Test
    public void queryOne(){
        assertEquals("ff80818153786ef9015378d49f050006",feedbackService.findOne("ff80818153786ef9015378d49f050006").getId());
    }

    @Test(expected = ResourceNotFoundException.class)
    public void deleteResourceNotFound(){
        feedbackService.delete("11223344");
    }

/*    @Test
    public void delete(){
        String id = "";
        feedbackService.delete("id");
        assertNull(feedbackService.findOne(id));
    }*/

    @Test(expected = ResourceInvalidedException.class)
    public void createInvalidDTO(){
        FeedbackDTO feedbackDTO = new FeedbackDTO();
        feedbackService.create(feedbackDTO);
    }

}
