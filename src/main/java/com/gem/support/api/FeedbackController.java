package com.gem.support.api;

import com.gem.support.persistent.model.QFeedback;
import com.gem.support.service.FeedbackBriefService;
import com.gem.support.service.FeedbackService;
import com.gem.support.service.dto.CompanyFeedbackDTO;
import com.gem.support.service.dto.FeedbackBriefDTO;
import com.gem.support.service.dto.FeedbackDTO;
import com.gem.support.util.StatisticUtil;
import com.mysema.query.types.Predicate;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {

    private final static int COLUMN_USER = 1;
    private final static String COLUMN_USER_STR = "Username";
    
    private final static int COLUMN_COUNT = 3;
    private final static String COLUMN_COUNT_STR = "Total";
    
    private final static int COLUMN_TIME  = 5;
    private final static String COLUMN_TIME_STR = "Time";
    
    private final static int COLUMN_CONTENT = 6;
    private final static String COLUMN_CONTENT_STR = "Content";

    private final static String COLUMN_COMPANY_NAME = "Company";
    private final static int COLUMN_COMPANY_STT = 1;

    private final static String COLUMN_TOTAL_FEEDBACK = "Total feedback";
    private final static int COLUMN_TOTAL_FEEDBACK_STT = 3;
    
    private final static String COLUMN_TOTAL_STAFF = "Total user";
    private final static int COLUMN_TOTAL_STAFF_STT = 2;
    
    @Autowired
    private FeedbackService feedbackService;

    @Autowired
    private FeedbackBriefService feedbackBriefService;

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

    @RequestMapping(value = "/statistic")
    public void statistic(Pageable pageable, final HttpServletRequest request, final HttpServletResponse response) {

        HSSFWorkbook workbook = new HSSFWorkbook();
        List<CompanyFeedbackDTO> companyWithTicket = feedbackService.getCompanyWithTicket(pageable).getContent();
        companyWithTicket.forEach(companyFeedbackDTO -> {

            List<FeedbackDTO> feedbackDTOList = feedbackService.findAllByCompanyId(StatisticUtil.getPageable(),companyFeedbackDTO.getUuid()).getContent();
            HashMap<String, List<FeedbackDTO>> listHashMap = StatisticUtil.getListUserWithFeedback(feedbackDTOList);
            
            //overview
            HSSFSheet sheet = workbook.createSheet(companyFeedbackDTO.getCompanyName());
            HSSFRow row = sheet.createRow(0);
            row.createCell(COLUMN_COMPANY_STT).setCellValue(COLUMN_COMPANY_NAME);
            row.createCell(COLUMN_TOTAL_STAFF_STT).setCellValue(COLUMN_TOTAL_STAFF);
            row.createCell(COLUMN_TOTAL_FEEDBACK_STT).setCellValue(COLUMN_TOTAL_FEEDBACK);

            HSSFRow rowInfo = sheet.createRow(1);
            rowInfo.createCell(COLUMN_COMPANY_STT).setCellValue(companyFeedbackDTO.getCompanyName());
            rowInfo.createCell(COLUMN_TOTAL_STAFF_STT).setCellValue(listHashMap.size());
            rowInfo.createCell(COLUMN_TOTAL_FEEDBACK_STT).setCellValue(feedbackDTOList.size());

            sheet.createRow(2);
            sheet.createRow(3);
            //detail
            HSSFRow row0 = sheet.createRow(4);
            row0.createCell(COLUMN_USER).setCellValue(COLUMN_USER_STR);
            row0.createCell(COLUMN_USER+1).setCellValue("");
            row0.createCell(COLUMN_COUNT).setCellValue(COLUMN_COUNT_STR);
            row0.createCell(COLUMN_COUNT+1).setCellValue("");
            row0.createCell(COLUMN_TIME).setCellValue(COLUMN_TIME_STR);
            row0.createCell(COLUMN_CONTENT).setCellValue(COLUMN_CONTENT_STR);


            StatisticUtil.mergeXls(sheet,4,4,1,2);
            StatisticUtil.mergeXls(sheet,4,4,3,4);
            int rowData = 5;
            for(String key : listHashMap.keySet()) {

                List<FeedbackDTO> feedbackDTOs = listHashMap.get(key);
                boolean isFirst = true;
                for(FeedbackDTO fb : feedbackDTOs){
                    HSSFRow row2 = sheet.createRow(rowData++);
                    if(isFirst){
                        row2.createCell(1).setCellValue(fb.getUserInfo().getUserId() + " / " + fb.getUserInfo().getUsername());
                        row2.createCell(2).setCellValue("");
                        row2.createCell(3).setCellValue(feedbackDTOs.size());
                        row2.createCell(4).setCellValue("");
                        isFirst = false;
                    }
                    row2.createCell(5).setCellValue(fb.getTime().toLocaleString());
                    row2.createCell(6).setCellValue(fb.getContent());
                }

                StatisticUtil.mergeXls(sheet,rowData - feedbackDTOs.size(),rowData - 1,1,2);
                StatisticUtil.mergeXls(sheet,rowData - feedbackDTOs.size(),rowData - 1,3,4);

            }

        });

        try {
            OutputStream output = response.getOutputStream();
            response.reset();
            workbook.write(output);
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=\"" + "statistic.xls" + "\"");
            output.flush();
        } catch (IOException e) {
        }

    }


}
