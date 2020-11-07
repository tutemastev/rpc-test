package rpc.common;

import java.io.Serializable;

public class RequestBeanDTO<T> implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 585410020925710408L;

    /**
     * 请求方法名称
     */
    private String methodName;

    /**
     * 请求接口名称
     */
    private String interfaceName;
    
    /**
     * 方法参数
     */
    private T Data;

    public T getData() {
        return Data;
    }

    public void setData(T data) {
        Data = data;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

}
