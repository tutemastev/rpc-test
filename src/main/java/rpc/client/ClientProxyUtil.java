package rpc.client;

import java.lang.reflect.Proxy;

public class ClientProxyUtil {

    public static Object getProxyInstance(Class<?> cls) {
        Object proxyInstance = null;
        Class<?>[] Interfaces = new Class[]{cls};
        ClientProxy invocationHandler = null;
        if(cls.isInterface()){
            invocationHandler = new ClientProxy();
        } else {
            throw new RuntimeException("代理类型非接口类型");
        }
        proxyInstance = Proxy.newProxyInstance(  
                cls.getClassLoader(),  
                Interfaces, 
                invocationHandler); 
        return proxyInstance;
    }
    
}
