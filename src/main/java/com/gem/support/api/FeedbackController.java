package com.gem.support.api;

import com.gem.support.persistent.model.QFeedback;
import com.gem.support.service.FeedbackBriefService;
import com.gem.support.service.FeedbackService;
import com.gem.support.service.dto.CompanyFeedbackDTO;
import com.gem.support.service.dto.FeedbackBriefDTO;
import com.gem.support.service.dto.FeedbackDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private FeedbackBriefService feedbackBriefService;

    public FeedbackController() {
    }

    public FeedbackController(FeedbackService feedbackService) {
        this.feedbackService = feedbackService;
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public Page<FeedbackBriefDTO> listFeedback(Pageable pageable) {
        return feedbackBriefService.findAll(pageable);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public void create(@RequestBody FeedbackDTO feedbackDTO) {
        feedbackService.create(feedbackDTO);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public FeedbackDTO findOne(@PathVariable("id") String id) {
        return feedbackService.findOne(id);
    }

    @RequestMapping(value = "/{id}/delete", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") String id) {
        feedbackService.delete(id);
    }

    @RequestMapping(value = "/company/{id}", method = RequestMethod.GET)
    public Page<FeedbackBriefDTO> listFeedback(@PathVariable("id") String id, Pageable pageable) {
        return feedbackBriefService.findAllByCompanyId(id, pageable);
    }

    @RequestMapping(value = "/company", method = RequestMethod.GET)
    public Page<CompanyFeedbackDTO> companyWithFeedback(Pageable pageable) {
        return feedbackService.getCompanyWithTicket(pageable);
    }

    @RequestMapping(value = "search", method = RequestMethod.GET)
    public Page<FeedbackDTO> search(@RequestParam(value = "q", defaultValue = "", required = false) String keyword) {
        return null;
    }

}
