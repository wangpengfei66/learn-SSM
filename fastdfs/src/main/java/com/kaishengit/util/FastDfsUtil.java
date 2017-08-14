package com.kaishengit.util;

import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

public class FastDfsUtil {

    public FastDfsUtilResult uploadFile(byte[] bytes,String extName) {
        return uploadFile(bytes,extName,null);
    }

    public FastDfsUtilResult uploadFile(byte[] bytes, String extName, Map<String,String> metaDataMap) {
        try {
            StorageClient storageClient = getStorageClient();
            //判断metaDataMap是否为null
            NameValuePair[] nameValuePairs = null;
            if(metaDataMap != null) {
                List<NameValuePair> nameValuePairList = new ArrayList<NameValuePair>();
                for(Map.Entry<String,String> entry : metaDataMap.entrySet()) {
                    nameValuePairList.add(new NameValuePair(entry.getKey(),entry.getValue()));
                }
                nameValuePairs = nameValuePairList.toArray(new NameValuePair[]{});
            }
            String[] result = storageClient.upload_file(bytes,extName,nameValuePairs);
            return new FastDfsUtilResult(result[0],result[1]);
        } catch (Exception ex) {
            throw new RuntimeException("上传FastDFS异常",ex);
        }
    }

    /**
     * 文件下载，获取到字节文件流
     * @param groupName
     * @param filePath
     * @return
     */
    public byte[] downloadFile(String groupName,String filePath) {
        try {
            StorageClient storageClient = getStorageClient();
            return storageClient.download_file(groupName,filePath);
        } catch (Exception e) {
            throw new RuntimeException("FastDFS文件下载异常",e);
        }
    }

    /**
     * 获取文件信息
     * @param groupName
     * @param filePath
     * @return
     */
    public FileInfo getFileInfo(String groupName,String filePath){
        try {
            StorageClient storageClient = getStorageClient();
            return storageClient.get_file_info(groupName,filePath);
        } catch (Exception ex) {
            throw new RuntimeException("获取文件信息异常",ex);
        }
    }

    public NameValuePair[] getNameValuePair(String groupName, String filePath) {
        try {
            StorageClient storageClient = getStorageClient();
            return storageClient.get_metadata(groupName,filePath);
        } catch (Exception ex) {
            throw new RuntimeException("获取文件信息异常",ex);
        }
    }

    public void deleteFile(String groupName,String filePath) {
        try {
            StorageClient storageClient = getStorageClient();
            storageClient.delete_file(groupName,filePath);
        } catch (Exception e) {
            throw new RuntimeException("FastDFS获取文件信息异常",e);
        }
    }

    /**
     * 获取客户端对象
     * @return
     * @throws IOException
     * @throws MyException
     */
    private StorageClient getStorageClient() throws IOException, MyException {
        //1.配置Tracker的地址
        Properties properties = new Properties();
        properties.setProperty(ClientGlobal.PROP_KEY_TRACKER_SERVERS,"192.168.209.128:22122");
        ClientGlobal.initByProperties(properties);
        //获取Tracker的服务器
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取StorageClient
        StorageClient storageClient = new StorageClient(trackerServer,null);
        return storageClient;
    }


}
