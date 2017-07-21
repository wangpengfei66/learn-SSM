package com.kaishengit.test;

import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.service.CustomerService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext*.xml")
public class ExportExcel {
    @Autowired
    private CustomerService customerService;

    @Test
    public void exportCsv() throws Exception{
        List<Customer> customerList = customerService.findCustomerById(1008);

        OutputStream outputStream = new FileOutputStream("G:/temp/customer.csv");
        OutputStreamWriter writer = new OutputStreamWriter(outputStream,"GBK");

        writer.write("姓名,职位,级别,联系方式\r\n");
        for(Customer customer : customerList) {
            writer.write(customer.getCustName() + "," + customer.getJob() + ","+customer.getLevel()+ "," +customer.getTel()+"\r\n");
        }
        writer.flush();
        writer.close();
    }
    @Test
    public void exportXls() throws Exception {
        List<Customer> customerList = customerService.findCustomerById(1008);
        //创建工作表
        Workbook workbook = new HSSFWorkbook();
        //创建sheet标签页
        Sheet sheet = workbook.createSheet("客户资料");
        //创建数据
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellValue("客户名称");
        row.createCell(1).setCellValue("职位");
        row.createCell(2).setCellValue("级别");
        row.createCell(3).setCellValue("联系电话");
        for(int i = 0;i < customerList.size();i++) {
            Customer customer = customerList.get(i);
            Row dataRow = sheet.createRow(1+i);
            dataRow.createCell(0).setCellValue(customer.getCustName());
            dataRow.createCell(1).setCellValue(customer.getJob());
            dataRow.createCell(2).setCellValue(customer.getLevel());
            dataRow.createCell(3).setCellValue(customer.getTel());
        }
        //将工作表写到磁盘上
        FileOutputStream outputStream = new FileOutputStream("G:/temp/customer.xls");
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }


}
