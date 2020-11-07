package rpc.common;

import java.io.Serializable;

public class RequestBeanDTO implements Serializable {
	
	public RequestBeanDTO() {}
	
	public RequestBeanDTO(String mName, String iName, Object[] data) {
		this.data = data;
		this.interfaceName = iName;
		this.methodName = mName;
	}

    /**
     * 
     */
    private static final long serialVersionUID = 585410020925710408L;

    private String methodReturnClassName;
    
    /**
	 * @return the methodReturnClassName
	 */
	public String getMethodReturnClassName() {
		return methodReturnClassName;
	}

	/**
	 * @param methodReturnClassName the methodReturnClassName to set
	 */
	public void setMethodReturnClassName(String methodReturnClassName) {
		this.methodReturnClassName = methodReturnClassName;
	}

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
    private Object[] data;

    /**
	 * @return the data
	 */
	public Object[] getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Object[] data) {
		this.data = data;
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
