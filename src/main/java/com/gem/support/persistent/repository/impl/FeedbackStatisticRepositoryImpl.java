package com.gem.support.persistent.repository.impl;

import com.gem.support.persistent.repository.FeedBackStatisticRepository;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by vanhop on 3/18/16.
 */
public class FeedbackStatisticRepositoryImpl implements FeedBackStatisticRepository{

    @Override
    public File getStatisticFile() {

        HSSFWorkbook workbook = new HSSFWorkbook();  // or new XSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("lawix10");
        HSSFRow rowhead = sheet.createRow((short) 0);
        rowhead.createCell((short) 0).setCellValue("CellHeadName1");
        rowhead.createCell((short) 1).setCellValue("CellHeadName2");
        rowhead.createCell((short) 2).setCellValue("CellHeadName3");

        int i = 1;
        while (i < 10){
            HSSFRow row = sheet.createRow((short) i);
            row.createCell((short) 0).setCellValue("Test 0" + i);
            row.createCell((short) 1).setCellValue("Test 1" + i);
            row.createCell((short) 2).setCellValue("Test 2" + i);
            i++;

        }

        String yemi = System.getProperty("user.dir") + "/test.xls";
        FileOutputStream fileOut = null;
        try {
            fileOut = new FileOutputStream(yemi);
            workbook.write(fileOut);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
