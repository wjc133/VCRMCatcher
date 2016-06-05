package com.elite.tools.catcher.client.export;

import com.elite.tools.catcher.core.constant.ContentVo;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

/**
 * Created by wjc133
 * DATE: 16/5/27
 * TIME: 下午10:06
 */
public class ExcelExportService {
    public void export(List<ContentVo> data, OutputStream out) {
        String title = "客户信息";
        String[] headers = {"账户ID", "账户名称", "公司名称", "公司地址", "网址", "费用到期", "联系方式"};

        // 声明一个工作薄
        HSSFWorkbook workbook = new HSSFWorkbook();
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        // 设置表格默认列宽度为15个字节
        sheet.setDefaultColumnWidth((short) 15);

        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.VIOLET.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 把字体应用到当前的样式
        style.setFont(font);

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        for (short i = 0; i < headers.length; i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellStyle(style);
            HSSFRichTextString text = new HSSFRichTextString(headers[i]);
            cell.setCellValue(text);
        }

        // 遍历集合数据，产生数据行
        Iterator<ContentVo> it = data.iterator();
        int index = 0;
        while (it.hasNext()) {
            index++;
            row = sheet.createRow(index);
            ContentVo vo = it.next();

            HSSFCell cell = row.createCell(0);
            cell.setCellValue(vo.getAcctId());

            HSSFCell cell1 = row.createCell(1);
            cell1.setCellValue(vo.getAcctName());

            HSSFCell cell2 = row.createCell(2);
            cell2.setCellValue(vo.getCompanyName());

            HSSFCell cell3 = row.createCell(3);
            cell3.setCellValue(vo.getAddressDetail());

            HSSFCell cell4 = row.createCell(4);
            cell4.setCellValue(vo.getSiteUrl());

            HSSFCell cell5 = row.createCell(5);
            cell5.setCellValue(vo.getRechargeDate());

            HSSFCell cell6 = row.createCell(6);
            String datas = vo.getPhoneDatas();
            if (StringUtils.isNotEmpty(datas)) {
                cell6.setCellValue(datas);
            }
        }
        try {
            workbook.write(out);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
