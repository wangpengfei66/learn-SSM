package com.kaishengit.crm.serviceImpl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Account;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.CustomerExample;
import com.kaishengit.crm.mapper.CustomerMapper;
import com.kaishengit.crm.service.CustomerService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class CustomerServiceImpl implements CustomerService{


    @Value("#{'${customer.trade}'.split(',')}") //SpringEL表达式
    private List<String> tradeList;
    @Value("#{'${customer.source}'.split(',')}")
    private List<String> sourceList;
    @Autowired
    private CustomerMapper customerMapper;

    /**
     * 获取所有行业数据
     * @return
     */
    @Override
    public List<String> getTrade() {
        return tradeList;
    }

    /**
     * 获取所有客户来源数据
     * @return
     */
    @Override
    public List<String> getSource() {
        return sourceList;
    }

    /**
     * 新增客户
     * @param customer
     * @param id
     */
    @Override
    public void saveCust(Customer customer, Integer id) {
        customer.setAccountId(id);
        customer.setCreateTime(new Date());
        customerMapper.insert(customer);
    }

    /**
     * 根据条件查询特定账户下的客户列表
     * @param queryParams 先分页，pageHelper.startPage
     * @return 需要先查询出来list，然后在new PageInfo的时候将list传进去
     */
    @Override
    public PageInfo<Customer> findPageByParams(Map<String, Object> queryParams) {
        Integer pageNum = (Integer) queryParams.get("pageNum");
        PageHelper.startPage(pageNum,5);
        List<Customer> customerList = customerMapper.findByParams(queryParams);
        return new PageInfo<Customer>(customerList);
    }

    @Override
    public Customer findById(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }

    @Override
    public void editCust(Customer customer) {
        customerMapper.updateByPrimaryKeySelective(customer);
    }

    @Override
    public void delById(Integer id) {
        //TODO 删除跟进记录
        //TODO 删除日程安排
        //TODO 删除相关资料
        //删除客户 物理删除，逻辑删除，是将状态改为不可用即可
        customerMapper.deleteByPrimaryKey(id);
    }

    /**
     * 将客户放入公海
     * @param customer
     */
    @Override
    public void shareCustomerToPublic(Customer customer, Account account) {
        customer.setAccountId(null);
        customer.setReminder(account.getUserName() + "将客户[" + customer.getCustName() + "]放入公海");
        customerMapper.updateByPrimaryKey(customer);
    }

    @Override
    public void transferCustomerToOtherAccount(Customer customer, Integer accountId,Account account) {
        customer.setAccountId(accountId);
        customer.setReminder(account.getUserName() + "将客户[" + customer.getCustName() + "]转交给您");
        customerMapper.updateByPrimaryKey(customer);
    }

    @Override
    public List<Customer> findCustomerById(Integer i) {
        CustomerExample customerExample = new CustomerExample();
        customerExample.createCriteria().andAccountIdEqualTo(i);
        return customerMapper.selectByExample(customerExample);
    }

    @Override
    public void exportAccountCustomerToExcel(Account account, OutputStream outputStream) {
        List<Customer> customerList = findCustomerById(account.getId());
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
        try {
            workbook.write(outputStream);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    @Override
    public List<Customer> findPublicCustomer() {
        CustomerExample customerExample = new CustomerExample();
        customerExample.createCriteria().andAccountIdIsNull();
        return customerMapper.selectByExample(customerExample);
    }

    /*
     * 返回公海客户的page对象
     * @param  查询所有客户，然后分页
     * @param queryParams 先分页，pageHelper.startPage
     * @return 需要先查询出来list，然后在new PageInfo的时候将list传进去
     * @return
     */
    @Override
    public PageInfo<Customer> findPublicPageByParams(Map<String, Object> queryParams) {
        Integer pageNum = (Integer) queryParams.get("pageNum");
        PageHelper.startPage(pageNum,5);
        List<Customer> customerList = findPublicCustomer();
        return new PageInfo<>(customerList);
    }

    /**
     * 查询所有的客户
     * @return
     */
    @Override
    public List<Customer> findAll() {
        return customerMapper.selectByExample(new CustomerExample());
    }

    @Override
    public Customer findOneCustomerById(Integer id) {
        return customerMapper.selectByPrimaryKey(id);
    }


}
