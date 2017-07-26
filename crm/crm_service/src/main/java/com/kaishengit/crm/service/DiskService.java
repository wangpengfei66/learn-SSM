package com.kaishengit.crm.service;

import com.kaishengit.crm.entity.Disk;

import java.io.InputStream;
import java.util.List;

public interface DiskService {

    List<Disk> findByPId(Integer pId);

    Disk findById(Integer pId);

    void saveNewFolder(Disk disk);

    void saveNewFile(Disk disk, InputStream inputStream);
}
