package rpc.common;

import java.io.Serializable;

public class ResponseBeanDTO<T> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 8646983695843358582L;

    /**
     * 结果码
     */
    private String resultCode;

    /**
     * 结果说明
     */
    private String resultMsg;

    /**
     * 响应结果
     */
    private T data;

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
