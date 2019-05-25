package com.example.test;

/**
 * Created by Administrator on 2019/4/30.
 */
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRichTextString;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Test;
import java.io.OutputStream;
import java.util.List;

public class exportExcel {
    public void exportExcel(XSSFWorkbook workbook, int sheetNum,String sheetTitle, String headers, List<List<String>> result,
                            OutputStream out,XSSFCellStyle style,XSSFCellStyle style1,XSSFCellStyle style2,XSSFCellStyle style3) throws Exception {
     // 第一步，创建一个webbook，对应一个Excel以xsl为扩展名文件
        XSSFSheet sheet = workbook.createSheet();
        workbook.setSheetName(sheetNum, sheetTitle);
        CellRangeAddress callRangeAddress0 = new CellRangeAddress(0,0,0,7);//起始行,结束行,起始列,结束列
        CellRangeAddress callRangeAddress1 = new CellRangeAddress(1,1,0,7);//起始行,结束行,起始列,结束列
        CellRangeAddress callRangeAddress2 = new CellRangeAddress(3,5,0,0);//起始行,结束行,起始列,结束列
        CellRangeAddress callRangeAddress3 = new CellRangeAddress(3,4,1,2);//起始行,结束行,起始列,结束列
        CellRangeAddress callRangeAddress4 = new CellRangeAddress(3,5,3,3);//起始行,结束行,起始列,结束列
        CellRangeAddress callRangeAddress5 = new CellRangeAddress(3,4,4,4);//起始行,结束行,起始列,结束列
        CellRangeAddress callRangeAddress6 = new CellRangeAddress(3,4,5,5);//起始行,结束行,起始列,结束列
        CellRangeAddress callRangeAddress7 = new CellRangeAddress(3,5,6,6);//起始行,结束行,起始列,结束列
        CellRangeAddress callRangeAddress8 = new CellRangeAddress(3,4,7,7);//起始行,结束行,起始列,结束列
        CellRangeAddress callRangeAddress9 = new CellRangeAddress(6,8,0,7);//起始行,结束行,起始列,结束列

        sheet.addMergedRegion(callRangeAddress0);
        sheet.addMergedRegion(callRangeAddress1);
        sheet.addMergedRegion(callRangeAddress2);
        sheet.addMergedRegion(callRangeAddress3);
        sheet.addMergedRegion(callRangeAddress4);
        sheet.addMergedRegion(callRangeAddress5);
        sheet.addMergedRegion(callRangeAddress6);
        sheet.addMergedRegion(callRangeAddress7);
        sheet.addMergedRegion(callRangeAddress8);
        sheet.addMergedRegion(callRangeAddress9);


        //设置列宽度大小
        //sheet.setDefaultColumnWidth((short) 20);
        sheet.setDisplayGridlines(false);
        sheet.setColumnWidth(0,5*256);
        sheet.setColumnWidth(1,3*256);
        sheet.setColumnWidth(2,3*256);
        sheet.setColumnWidth(3,30*256);
        sheet.setColumnWidth(4,23*256);
        sheet.setColumnWidth(5,23*256);
        sheet.setColumnWidth(6,1152);
        sheet.setColumnWidth(7,23*256);
        sheet.setDisplayGridlines(false);

        XSSFRow row = sheet.createRow(0);
        row.setHeight((short) (33*20));
        XSSFCell cell = row.createCell(0);
        cell.setCellStyle(style);
        cell.setCellValue(headers.toString());

        row = sheet.createRow(1);
        row.setHeight((short) (46*20));
        cell = row.createCell(0);
        cell.setCellStyle(style3);
        cell.setCellValue("总账科目:银行存款\t科目编码:101\t明细科目:银行存款\n" + "明细科目:银行存款                                                   日期：2017-8-8/2017-11-10\n" + "科目编码:101                                                        套帐：001 清算总账户      ");

        sheet.setDisplayGridlines(true);
        row = sheet.createRow(3);
        cell = row.createCell(0);
        cell.setCellStyle(style1);
        cell.setCellValue("行号");

        cell = row.createCell(1);
        cell.setCellStyle(style1);
        cell.setCellValue("2017年度");

        cell = row.createCell(3);
        cell.setCellStyle(style1);
        cell.setCellValue("摘要");

        cell = row.createCell(4);
        cell.setCellStyle(style1);
        cell.setCellValue("借方");

        cell = row.createCell(5);
        cell.setCellStyle(style1);
        cell.setCellValue("贷方");

        cell = row.createCell(6);
        cell.setCellStyle(style1);
        cell.setCellValue("借贷");

        cell = row.createCell(7);
        cell.setCellStyle(style1);
        cell.setCellValue("余额");

        row = sheet.createRow(5);
        cell = row.createCell(1);
        cell.setCellStyle(style1);
        cell.setCellValue("月");

        cell = row.createCell(2);
        cell.setCellStyle(style1);
        cell.setCellValue("日");

        cell = row.createCell(4);
        cell.setCellStyle(style2);
        cell.setCellValue("万千百十亿千百十万千百十元角分 ");

        cell = row.createCell(5);
        cell.setCellStyle(style2);
        cell.setCellValue("万千百十亿千百十万千百十元角分 ");

        cell = row.createCell(7);
        cell.setCellStyle(style2);
        cell.setCellValue("万千百十亿千百十万千百十元角分 ");



        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String defaultOutTail = "制表：管理员                                复核：                                审核：\r\n制表日期：" +sdf.format(d) ;

        row = sheet.createRow(6);
        cell = row.createCell(0);
        cell.setCellStyle(style3);
        cell.setCellValue(defaultOutTail);
// 第三步：遍历集合数据，产生数据行，开始插入数据
//        if (result != null) {
//            int index = 2;
//            for (List<String> m : result) {
//                row = sheet.createRow(index);
//                int cellIndex = 0;
//                for (String str : m) {
//                    XSSFCell cell1 = row.createCell((int) cellIndex);
//                    cell.setCellValue(str.toString());
//                    cellIndex++;
//                }
//                index++;
//            }
//        }
    }

    @Test
    public void testPOI(){

        String path=null;
        try {

            path="D:\\test2.xlsx";
//1、输出的文件地址及名称
            OutputStream out = new FileOutputStream(path);
//2、sheet表中的标题行内容，需要输入excel的汇总数据
            String summary = "嘉实基金管理有限公司_日记账簿-明细_专用表";
            List<List<String>> summaryData = new ArrayList<List<String>>();
            List<String> list1 = new ArrayList();
            list1.add("11");
            list1.add("22");
            List<String> list2 = new ArrayList();
            list2.add("11");
            list2.add("22");
            summaryData.add(list1);
            summaryData.add(list2);



             //3、生成格式是xlsx可存储103万行数据，如果是xls则只能存不到6万行数据
            XSSFWorkbook workbook = new XSSFWorkbook();

            //第二步， 生成表格第一行的样式和字体
            XSSFCellStyle style = workbook.createCellStyle();


            style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);


            // 生成一个字体
            XSSFFont font = workbook.createFont();
            font.setColor(HSSFColor.ORANGE.index);
            font.setFontHeight(17);
            font.setFontName("宋体"); //字体

            font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
            font.setUnderline(HSSFFont.U_SINGLE);
            // 把字体应用到当前的样式
            style.setFont(font);
            // 指定当单元格内容显示不下时自动换行
            style.setWrapText(false);
/////////////////////////////////////////////////////////////////////////////
            XSSFCellStyle style1 = workbook.createCellStyle();
            style1.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

            XSSFFont font1 = workbook.createFont();
            font1.setFontHeight(10.5);
            font1.setColor(HSSFColor.GREEN.index);
            style1.setFont(font1);
            style1.setWrapText(true);

            style.setBorderTop(HSSFCellStyle.BORDER_NONE);
            style.setBorderBottom(HSSFCellStyle.BORDER_NONE);
            style.setBorderLeft(HSSFCellStyle.BORDER_NONE);
            style.setBorderRight(HSSFCellStyle.BORDER_NONE);

/////////////////////////////////////////////////////////////////////////////
            XSSFCellStyle style2 = workbook.createCellStyle();
            style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

            XSSFFont font2 = workbook.createFont();
            font2.setFontHeight(8);
            font2.setColor(HSSFColor.GREEN.index);
            style2.setFont(font2);
            style2.setWrapText(true);
/////////////////////////////////////////////////////////////////////////////
            XSSFCellStyle style3 = workbook.createCellStyle();
            style3.setAlignment(HSSFCellStyle.ALIGN_CENTER);
            style3.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);

            XSSFFont font3 = workbook.createFont();
            font3.setFontHeight(10.5);
            style3.setFont(font3);
            style3.setWrapText(true);
            //第一个表格内容
            for (int i=0;i<2;i++){
                this.exportExcel(workbook, i, "日报汇总"+i, summary, summaryData, out,style,style1,style2,style3);
            }

            //将所有的数据一起写入，然后再关闭输入流。
            workbook.write(out);
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
