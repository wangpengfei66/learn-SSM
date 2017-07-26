package com.kaishengit.crm.controller;

import com.kaishengit.crm.entity.Disk;
import com.kaishengit.crm.service.DiskService;
import com.kaishengit.dto.AjaxResult;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("disk")
public class NetDiskController {
    @Autowired
    private DiskService diskService;
    @GetMapping
    public String home(@RequestParam(required = false,defaultValue = "0",name = "_") Integer pId, Model model) {
        List<Disk> diskList = diskService.findByPId(pId);
        if(0 != pId) {
            //如果pId不是0，则是查看子文件或者文件夹
            Disk disk = diskService.findById(pId);
            model.addAttribute("disk",disk);
        }
        model.addAttribute("diskList",diskList);
        return "disk/diskHome";
    }

    /**
     * 新建文件夹
     * @param disk
     * @return
     */
    @PostMapping("/new/folder")
    @ResponseBody
    public AjaxResult addFolder(Disk disk) {
        diskService.saveNewFolder(disk);
        List<Disk> diskList = diskService.findByPId(disk.getpId());
        return AjaxResult.success(diskList);
    }
    /**
     * 上传文件
     */
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult upload(@RequestParam MultipartFile file,Integer pId,Integer accountId) throws IOException {
        //获取原始文件的大小名字
        String fileName = file.getOriginalFilename();
        //获取大小
        Long size = file.getSize();
        //文件的输入流
        InputStream inputStream = file.getInputStream();
        Disk disk = new Disk();
        disk.setAccountId(accountId);
        disk.setpId(pId);
        disk.setFileSize(FileUtils.byteCountToDisplaySize(size));
        disk.setName(fileName);

        diskService.saveNewFile(disk,inputStream);
        List<Disk> diskList = diskService.findByPId(pId);
        return AjaxResult.success(diskList);
    }

}
