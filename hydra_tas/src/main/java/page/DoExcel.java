package page;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;

/**
 * 写入数据到Excel
 * @author http://www.lookhan.com
 *
 */
public class DoExcel {

    public static int rowIndex = 0, columnIndex = 0;
    public static Workbook wb;
    public static Sheet ws;
    public static Row wr;
    public static int row_num;
    public static int col_num;
    public static String fileName;
    public static String[][] testData;
    public static String whichWeek;

    public DoExcel (String fileName){
        this.fileName = fileName;
    }

    public String[][] ReadExcel () throws Exception {
        try {
            InputStream is = new FileInputStream(new File(fileName));
            //根据输入流创建Workbook对象
            wb = WorkbookFactory.create(is);
            //get到Sheet对象
            ws = wb.getSheet("Sheet1");
            wr = ws.getRow(0);
            // initial testData
            row_num = ws.getPhysicalNumberOfRows();
            col_num = wr.getPhysicalNumberOfCells();
            whichWeek = ws.getRow(0).getCell(2).getStringCellValue();  // the 1st row 3rd col has 3 options: This Week; Next Week; Last Week
            testData = new String[row_num-2][col_num]; // the first 2 rows are not valid datas
            //这个必须用接口
            for(int i = 0 ; i < row_num-2; i++) { // the first 2 rows are not valid datas
                for(int j = 0 ; j < col_num; j++){
                    Cell cell = ws.getRow(i+2).getCell(j); // start reading from 3rd row
                    // System.out.print("#"+cell+"&");   Don't leave your cell empty, or cell will be set null
                    switch(cell.getCellType()) {
                    case Cell.CELL_TYPE_BOOLEAN:
                    //得到Boolean对象的方法
                        testData[i][j] = String.valueOf(cell.getBooleanCellValue());
                        break;
                    case Cell.CELL_TYPE_NUMERIC:
                    //先看是否是日期格式
                        if(DateUtil.isCellDateFormatted(cell)){
                        //读取日期格式
                            testData[i][j] = String.valueOf(cell.getDateCellValue());
                        }else{
                        //读取数字
                                if (j == 3) {             // the col_3 is WO Code which has to be an integer
                                testData[i][j] = String.valueOf((int)cell.getNumericCellValue());
                                } else
                                testData[i][j] = String.valueOf(cell.getNumericCellValue());
                        }
                        break;
                    case Cell.CELL_TYPE_FORMULA:
                    //读取公式
                        testData[i][j] = String.valueOf(cell.getCellFormula());
                        break;
                    case Cell.CELL_TYPE_STRING:
                    //读取String
                        testData[i][j] = cell.getStringCellValue();
                        break;
                    }
                }
            }
        }   catch (FileNotFoundException e) {
            System.out.println("Could not find the Excel sheet");
            e.printStackTrace();
        }    catch (IOException e) {
            System.out.println("Could not read the Excel sheet");
            e.printStackTrace();
        }
        return testData;
    }

    public void WriteExcel() {
        try{
            Workbook wb = new XSSFWorkbook();
            CreationHelper createHelper = wb.getCreationHelper();
            //创建页
            Sheet sheet = wb.createSheet("sheet1");
            // 创建行
            Row row = sheet.createRow((short) 0);
            // 创建单元格
            row.createCell(0).setCellValue(258258258);
            row.createCell(1).setCellValue(0.67);
            row.createCell(2).setCellValue(createHelper.createRichTextString("http://www.lookhan.com"));
            row.createCell(3).setCellValue(createHelper.createRichTextString("Java知识笔记"));
            // 写入文件
            FileOutputStream fileOut = null;
            fileOut = new FileOutputStream("/Users/jack/lookhan.xlsx");
            wb.write(fileOut);
            fileOut.close();
            System.out.println("写入成功！");
        } catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
