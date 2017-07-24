package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.SaleChanceRecord;

import java.util.List;

public interface SaleChanceContentService {
    List<SaleChanceRecord> findRecordListBySaleChanceId(Integer id);


}
