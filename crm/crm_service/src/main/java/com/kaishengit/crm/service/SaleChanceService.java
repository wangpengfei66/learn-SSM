package com.kaishengit.crm.service;

import com.github.pagehelper.PageInfo;
import com.kaishengit.crm.entity.Customer;
import com.kaishengit.crm.entity.SaleChance;
import com.kaishengit.crm.entity.SaleChanceRecord;

import java.util.List;
import java.util.Map;

public interface SaleChanceService {

    List<String> getProgressList();

    void saveSaleChance(SaleChance saleChance);

    PageInfo<SaleChance> findByParams(Map<String, Object> queryParams);

    SaleChance findSaleChanceById(Integer id);

    void updateSaleChanceProgress(Integer id, String progress);

    void delSaleChanceById(Integer saleId);

    void addNewRecord(SaleChanceRecord saleChanceRecord);

    List<SaleChance> findByAccountIdAndCustomerId(Integer id, Integer id1);
}
