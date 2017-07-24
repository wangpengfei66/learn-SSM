package com.kaishengit.crm.serviceImpl;

import com.kaishengit.crm.entity.SaleChanceRecord;
import com.kaishengit.crm.entity.SaleChanceRecordExample;
import com.kaishengit.crm.mapper.SaleChanceRecordMapper;
import com.kaishengit.crm.service.SaleChanceContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class SaleChanceContentServiceImpl implements SaleChanceContentService {
    @Autowired
    private SaleChanceRecordMapper saleChanceRecordMapper;
    @Override
    public List<SaleChanceRecord> findRecordListBySaleChanceId(Integer id) {
        SaleChanceRecordExample saleChanceRecordExample = new SaleChanceRecordExample();
        saleChanceRecordExample.createCriteria().andSaleIdEqualTo(id);
        return saleChanceRecordMapper.selectByExampleWithBLOBs(saleChanceRecordExample);
    }


}
