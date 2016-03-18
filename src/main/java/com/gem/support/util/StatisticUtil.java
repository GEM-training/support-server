package com.gem.support.util;

import com.gem.support.service.dto.FeedbackDTO;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by vanhop on 3/18/16.
 */
public class StatisticUtil {

    public static HashMap<String,List<FeedbackDTO>> getListUserWithFeedback(List<FeedbackDTO> allByCompanyId){

        HashMap<String,List<FeedbackDTO>> listHashMap  = new HashMap<String,List<FeedbackDTO>>();
        for(FeedbackDTO feedbackDTO : allByCompanyId){

            if (listHashMap.get(feedbackDTO.getUserInfo().getUserId()) == null){
                ArrayList<FeedbackDTO> feedbackDTOs = new ArrayList<>();
                feedbackDTOs.add(feedbackDTO);
                listHashMap.put(feedbackDTO.getUserInfo().getUserId(), feedbackDTOs);
            } else {
                listHashMap.get(feedbackDTO.getUserInfo().getUserId()).add(feedbackDTO);
            }

        }


        return  listHashMap;
    }

    public static Pageable getPageable(){
        Pageable pageable1 = new Pageable() {
            @Override
            public int getPageNumber() {
                return 0;
            }

            @Override
            public int getPageSize() {
                return Integer.MAX_VALUE;
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
        return pageable1;
    }

    public static void mergeXls(HSSFSheet sheet, int firstRow, int lastRow, int firstColumn, int lastColumn){
        sheet.addMergedRegion(new CellRangeAddress(
                firstRow, //first row (0-based)
                lastRow, //last row  (0-based)
                firstColumn, //first column (0-based)
                lastColumn  //last column  (0-based)
        ));
    }

}
