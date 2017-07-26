package com.kaishengit.crm.serviceImpl;

import com.kaishengit.crm.entity.Disk;
import com.kaishengit.crm.entity.DiskExample;
import com.kaishengit.crm.mapper.DiskMapper;
import com.kaishengit.crm.service.DiskService;
import com.kaishengit.exception.ServiceException;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.nio.ch.IOUtil;

import java.io.*;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class DiskServiceImpl implements DiskService {
    @Autowired
    DiskMapper diskMapper;
    /**
     * 根据pid进行查找
     * @param pId
     * @return
     */
    @Override
    public List<Disk> findByPId(Integer pId) {
        DiskExample example = new DiskExample();
        example.createCriteria().andPIdEqualTo(pId);
        return diskMapper.selectByExample(example);
    }

    @Override
    public Disk findById(Integer id) {
        return diskMapper.selectByPrimaryKey(id);
    }

    /**
     * 添加新文件夹
     * @param disk
     */
    @Override
    public void saveNewFolder(Disk disk) {
        disk.setUpdateTime(new Date());
        disk.setType("dir");
        diskMapper.insert(disk);
    }

    /**
     * 存储文件，进数据库
     * @param disk
     * @param inputStream
     */
    @Override
    @Transactional
    public void saveNewFile(Disk disk, InputStream inputStream) {
        disk.setUpdateTime(new Date());
        disk.setUploadCount(0);
        disk.setType("file");
        String saveName = UUID.randomUUID() + disk.getName().substring(disk.getName().lastIndexOf("."));
        disk.setSaveName(saveName);
        diskMapper.insert(disk);
        //保存文件到磁盘
        try {
            OutputStream outputStream = new FileOutputStream(new File("G:/upload",saveName));
            IOUtils.copy(inputStream,outputStream);
            outputStream.flush();
            outputStream.close();
            inputStream.close();
        }catch (IOException e){
            throw new ServiceException("上传文件异常");
        }
    }
}
