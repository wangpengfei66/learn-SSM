package com.kaishengit.dto;

/**Ajax 请求的返回值
 * Created by Administrator on 2017/7/17.
 */
public class AjaxResult {
    public static final String SUCCESS= "success";
    public static final String ERROR = "error";

    public AjaxResult() {}

    public static AjaxResult success() {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setState(AjaxResult.SUCCESS);
        return ajaxResult;
    }
    public static AjaxResult success(Object data) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setState(AjaxResult.SUCCESS);
        ajaxResult.setObject(data);
        return ajaxResult;
    }
    public static AjaxResult error() {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setState(AjaxResult.ERROR);
        return ajaxResult;
    }
    public static AjaxResult error(String message) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.setMessage(message);
        ajaxResult.setState(AjaxResult.ERROR);
        return ajaxResult;
    }

    private String message;
    private String state;
    private Object object;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
