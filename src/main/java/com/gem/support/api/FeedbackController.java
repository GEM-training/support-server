package com.gem.support.api;

import com.gem.support.persistent.model.Feedback;
import com.gem.support.service.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void create(@RequestBody Feedback feedback){
        feedbackService.create(feedback);
    }


}
