package com.kaishengit.util;

public class FastDfsUtilResult {

    private String groupName;
    private String filePath;


    public FastDfsUtilResult() {}

    public FastDfsUtilResult(String groupName, String filePath) {
        this.groupName = groupName;
        this.filePath = filePath;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getAbsoluteResult() {
        return "/" + getGroupName() + "/" + getFilePath();
    }

}
