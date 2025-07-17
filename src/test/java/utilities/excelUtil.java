//package utilities;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileOutputStream;
//import java.io.IOException;
//import java.util.List;
//
//import org.apache.poi.xssf.usermodel.XSSFWorkbook;
//import org.apache.poi.xssf.usermodel.XSSFSheet;
//import org.apache.poi.xssf.usermodel.XSSFRow;
//
//public class excelUtil {
//
//    private static final String FILE_NAME = "data.xlsx";
//
//    private XSSFWorkbook getOrCreateWorkbook() throws IOException {
//        File file = new File(FILE_NAME);
//        if (file.exists()) {
//            try (FileInputStream fis = new FileInputStream(file)) {
//                return new XSSFWorkbook(fis);
//            }
//        } else {
//            return new XSSFWorkbook();
//        }
//    }
//
//    public void writeTitlePrice(String sheetName, List<String> titles, List<String> prices) throws Exception {
//        XSSFWorkbook workbook = null;
//        FileOutputStream fos = null;
//
//        try {
//            workbook = getOrCreateWorkbook();
//            XSSFSheet sheet = workbook.getSheet(sheetName);
//
//            if (sheet == null) {
//                sheet = workbook.createSheet(sheetName);
//                XSSFRow headerRow = sheet.createRow(0);
//                headerRow.createCell(0).setCellValue("Title");
//                headerRow.createCell(1).setCellValue("Price");
//            }
//
//            int rowNum = sheet.getLastRowNum() + 1;
//
//            for (int i = 0; i < titles.size(); i++) {
//                XSSFRow row = sheet.createRow(rowNum + i);
//                row.createCell(0).setCellValue(titles.get(i));
//                row.createCell(1).setCellValue(prices.get(i));
//            }
//
//            fos = new FileOutputStream(FILE_NAME);
//            workbook.write(fos);
//
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (workbook != null) {
//                try {
//                    workbook.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//
//    public void writeList(String sheetName, List<String> list) throws Exception {
//        XSSFWorkbook workbook = null;
//        FileOutputStream fos = null;
//
//        try {
//            workbook = getOrCreateWorkbook();
//            XSSFSheet sheet = workbook.getSheet(sheetName);
//
//            if (sheet == null) {
//                sheet = workbook.createSheet(sheetName);
//                XSSFRow headerRow = sheet.createRow(0);
//                headerRow.createCell(0).setCellValue("List");
//            }
//
//            int rowNum = sheet.getLastRowNum() + 1;
//
//            for (int i = 0; i < list.size(); i++) {
//                XSSFRow row = sheet.createRow(rowNum + i);
//                row.createCell(0).setCellValue(list.get(i));
//            }
//
//            fos = new FileOutputStream(FILE_NAME);
//            workbook.write(fos);
//
//        } finally {
//            if (fos != null) {
//                try {
//                    fos.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//            if (workbook != null) {
//                try {
//                    workbook.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}

package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class excelUtil {

    private static final String FILE_NAME = "data.xlsx";
    private static final Logger logger = LogManager.getLogger(excelUtil.class);

    private XSSFWorkbook getOrCreateWorkbook() throws IOException {
        File file = new File(FILE_NAME);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                logger.info("Loading existing workbook: " + FILE_NAME);
                return new XSSFWorkbook(fis);
            }
        } else {
            logger.info("Creating new workbook: " + FILE_NAME);
            return new XSSFWorkbook();
        }
    }

    // New helper method to clear a sheet's content
    private void clearSheet(XSSFSheet sheet) {
        if (sheet != null) {
            int lastRowNum = sheet.getLastRowNum();
            if (lastRowNum >= 0) { // Check if sheet has any rows
                logger.info("Clearing content of sheet: " + sheet.getSheetName());
                for (int i = 0; i <= lastRowNum; i++) {
                    XSSFRow row = sheet.getRow(i);
                    if (row != null) {
                        sheet.removeRow(row);
                    }
                }
                // After removing rows, you might need to shift up remaining rows
                // However, for a complete clear, simply creating new rows from 0 is sufficient.
                // We'll reset the row number logic in writeTitlePrice and writeList.
            }
        }
    }

    public void writeTitlePrice(String sheetName, List<String> titles, List<String> prices) throws Exception {
        XSSFWorkbook workbook = null;
        FileOutputStream fos = null;

        try {
            workbook = getOrCreateWorkbook();
            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
                logger.info("Created new sheet: " + sheetName);
            } else {
                // Clear existing content if the sheet already exists
                clearSheet(sheet);
            }

            // Create header row regardless of whether the sheet was new or cleared
            XSSFRow headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Title");
            headerRow.createCell(1).setCellValue("Price");
            logger.info("Added header to sheet: " + sheetName);


            int rowNum = 1; // Start writing data from the second row (index 1) after the header

            for (int i = 0; i < titles.size(); i++) {
                XSSFRow row = sheet.createRow(rowNum + i);
                row.createCell(0).setCellValue(titles.get(i));
                row.createCell(1).setCellValue(prices.get(i));
            }
            logger.info("Wrote " + titles.size() + " rows of Title/Price data to sheet: " + sheetName);


            fos = new FileOutputStream(FILE_NAME);
            workbook.write(fos);
            logger.info("Data written to " + FILE_NAME);

        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    logger.error("Error closing FileOutputStream: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    logger.error("Error closing workbook: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }

    public void writeList(String sheetName, List<String> list) throws Exception {
        XSSFWorkbook workbook = null;
        FileOutputStream fos = null;

        try {
            workbook = getOrCreateWorkbook();
            XSSFSheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                sheet = workbook.createSheet(sheetName);
                logger.info("Created new sheet: " + sheetName);
            } else {
                // Clear existing content if the sheet already exists
                clearSheet(sheet);
            }

            // Create header row regardless of whether the sheet was new or cleared
            XSSFRow headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("List");
            logger.info("Added header to sheet: " + sheetName);

            int rowNum = 1; // Start writing data from the second row (index 1) after the header

            for (int i = 0; i < list.size(); i++) {
                XSSFRow row = sheet.createRow(rowNum + i);
                row.createCell(0).setCellValue(list.get(i));
            }
            logger.info("Wrote " + list.size() + " rows of List data to sheet: " + sheetName);


            fos = new FileOutputStream(FILE_NAME);
            workbook.write(fos);
            logger.info("Data written to " + FILE_NAME);

        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    logger.error("Error closing FileOutputStream: " + e.getMessage());
                    e.printStackTrace();
                }
            }
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (IOException e) {
                    logger.error("Error closing workbook: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }
    }
}