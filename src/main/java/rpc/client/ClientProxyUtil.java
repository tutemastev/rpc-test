package rpc.client;

import java.lang.reflect.Proxy;

public class ClientProxyUtil {
	
	private static final ClientProxy INVOCATION_HANDLER = ClientProxy.getClientProxy();

    public static Object getProxyInstance(Class<?> cls) {
        Object proxyInstance = null;
        Class<?>[] Interfaces = new Class[]{cls};
        if(!cls.isInterface()){
            throw new RuntimeException("代理类型非接口类型");
        }
        proxyInstance = Proxy.newProxyInstance(  
                cls.getClassLoader(),  
                Interfaces, 
                INVOCATION_HANDLER);
        return proxyInstance;
    }
    
}
