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
import java.util.Arrays;
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

    /**
     * 修改文件名
     * @param disk
     */
    @Override
    public void updateFolder(Disk disk) {
        diskMapper.updateByPrimaryKeySelective(disk);
    }

    /**
     * 文件下载
     * @param disk
     * @param outputStream
     */
    @Override
    @Transactional
    public void fileDownload(Disk disk, OutputStream outputStream) {
        disk.setUploadCount(disk.getUploadCount() + 1);
        diskMapper.updateByPrimaryKeySelective(disk);
        try {
            File file = new File("G:/upload",disk.getSaveName());
            if(file.exists()) {
                InputStream inputStream = new FileInputStream(file);
                IOUtils.copy(inputStream,outputStream);
                outputStream.flush();
                outputStream.close();
                inputStream.close();
            }else{
                throw new ServiceException("资源不存在或已被删除");
            }
        } catch (IOException e) {
            throw new ServiceException("文件下载异常",e);
        }
    }

    /**
     * 递归删除树形结构
     * @param disk
     */
    @Override
    @Transactional
    public void delById(Disk disk) {
        if(disk.getType().equals("file")) {
            diskMapper.deleteByPrimaryKey(disk.getId());
            return;
        }else {
            //即将被删的list
            int pId = disk.getId();
            DiskExample example = new DiskExample();
            example.createCriteria().andPIdEqualTo(pId);
            //找到了父task的下一层
            List<Disk> diskList = diskMapper.selectByExample(example);
            if(diskList != null) {
                for(Disk disk1 : diskList) {
                    delById(disk1);
                    // TODO 不能删除自己
                    del(diskList,pId);
                }
            }else {
                return;
            }
        }
    }

    private void del(List<Disk> diskList,Integer id) {
        for(Disk disk : diskList) {
            diskMapper.deleteByPrimaryKey(disk.getId());
        }
        diskMapper.deleteByPrimaryKey(id);
    }

}
