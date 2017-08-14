package com.kaishengit;

import com.kaishengit.util.FastDfsUtil;
import com.kaishengit.util.FastDfsUtilResult;
import org.apache.commons.io.IOUtils;
import org.csource.common.MyException;
import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class FastDfsTest {

    @Test
    public void uploadTest() throws IOException, MyException {
        //1.配置Tracker的地址
        Properties properties = new Properties();
        properties.setProperty(ClientGlobal.PROP_KEY_TRACKER_SERVERS,"192.168.209.128:22122");
        ClientGlobal.initByProperties(properties);
        //获取Tracker的服务器
        TrackerClient trackerClient = new TrackerClient();
        TrackerServer trackerServer = trackerClient.getConnection();
        //获取StorageClient
        StorageClient storageClient = new StorageClient(trackerServer,null);
        FileInputStream fileInputStream = new FileInputStream("G:/upload/1.jpg");
        //设置文件信息
        NameValuePair [] nameValuePairs = new NameValuePair[3];
        nameValuePairs[0] = new NameValuePair("width","100");
        nameValuePairs[1] = new NameValuePair("length","200");
        nameValuePairs[2] = new NameValuePair("locations","beijing");

        String[] result = storageClient.upload_appender_file(IOUtils.toByteArray(fileInputStream),"jpg",nameValuePairs);
        System.out.println("GroupName: " + result[0]);
        System.out.println("FilePath: " + result[1]);
    }

    @Test
    public void uploadImage() throws IOException {
        FileInputStream fileInputStream = new FileInputStream("G:/upload/2.jpg");
        FastDfsUtil fastDfsUtil = new FastDfsUtil();
        FastDfsUtilResult result = fastDfsUtil.uploadFile(IOUtils.toByteArray(fileInputStream),"jpg");
        System.out.println(result.getAbsoluteResult());
    }

    @Test
    public void downLoadFile() throws IOException {
        FastDfsUtil fastDfsUtil = new FastDfsUtil();
        byte[] bytes = fastDfsUtil.downloadFile("group1","M00/00/00/wKjRgFmRcVWAMMakAAAAAAAAAAA87..jpg");
        FileOutputStream fileOutputStream = new FileOutputStream("G:/1.jpg");
        fileOutputStream.write(bytes);
        fileOutputStream.flush();
        fileOutputStream.close();
    }
    @Test
    public void getFileInfo() {
        FastDfsUtil fastDfsUtil = new FastDfsUtil();
        FileInfo fileInfo = fastDfsUtil.getFileInfo("group1","M00/00/00/wKjRgFmQemCEDVCtAAAAADHP_78040.jpg");
        System.out.println(fileInfo.getCreateTimestamp());
        System.out.println(fileInfo.getCrc32());
        System.out.println(fileInfo.getFileSize());
        System.out.println(fileInfo.getSourceIpAddr());
    }

    @Test
    public void getNamePair() {
        FastDfsUtil fastDfsUtil = new FastDfsUtil();
        NameValuePair[] nameValuePairs = fastDfsUtil.getNameValuePair("group1","M00/00/00/wKjRgFmQemCEDVCtAAAAADHP_78040.jpg");
        for(NameValuePair valuePair : nameValuePairs) {
            System.out.println(valuePair.getName() + " -> " + valuePair.getValue());
        }
    }
}
