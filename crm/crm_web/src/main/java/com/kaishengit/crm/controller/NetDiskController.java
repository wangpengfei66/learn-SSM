package com.kaishengit.crm.controller;

import com.kaishengit.crm.controller.exception.NotFoundException;
import com.kaishengit.crm.entity.Disk;
import com.kaishengit.crm.service.DiskService;
import com.kaishengit.dto.AjaxResult;
import com.kaishengit.exception.ServiceException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

@Controller
@RequestMapping("/disk")
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

    /**
     * 重命名
     */
    @PostMapping("/reName")
    @ResponseBody
    public AjaxResult reName(Disk disk) {
        diskService.updateFolder(disk);
        List<Disk> diskList = diskService.findByPId(disk.getpId());
        return AjaxResult.success(diskList);
    }
    /**
     * 文件下载
     */
    @GetMapping("/download")
    public void fileDownload(@RequestParam(name = "_") Integer id, HttpServletResponse response) throws IOException {
        Disk disk = diskService.findById(id);
        if(disk == null || disk.getType().equals("dir")) {
            throw new NotFoundException();
        }
        //设定响应头 输出流的响应头 //设定的输出流，浏览器不认识，就不会自动解析了
        response.setContentType("application/octet-stream");
        //设置下载时弹出中文框的文件名
        //处理中文的文件名  UTF-8 --> ISO8859-1
        String fileName = new String(disk.getName().getBytes("UTF-8"),"ISO8859-1");
        response.setHeader("Content-Disposition","attachment; filename="+fileName+"");
        //获取相应输出流
        OutputStream outputStream = response.getOutputStream();
        diskService.fileDownload(disk,outputStream);
    }
    /**
     * 文件删除
     */
    @GetMapping("/del/{taskId:\\d+}")
    @ResponseBody
    public AjaxResult fileDelete(@PathVariable Integer taskId) {
        Disk disk = diskService.findById(taskId);
        if(disk == null) {
            throw new NotFoundException();
        }
        diskService.delById(disk);
        return AjaxResult.success();
    }
}

