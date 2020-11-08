package rpc.common;

import java.io.Serializable;

public class ResponseBeanDTO implements Serializable {

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
    private Object data;
    
    private Exception exception;

	/**
	 * @return the exception
	 */
	public Exception getException() {
		return exception;
	}

	/**
	 * @param exception the exception to set
	 */
	public void setException(Exception exception) {
		this.exception = exception;
	}

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

	/**
	 * @return the data
	 */
	public Object getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public String toString() {
		
		return data.toString();
	}
	
	

}
