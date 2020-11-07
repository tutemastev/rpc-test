package rpc.service;

import java.lang.reflect.Proxy;

public class ServiceProxyUtil {

    public static Object getProxyInstance(Class<?> cls) {
        Object proxyInstance = null;
        Class<?>[] Interfaces = new Class[]{cls};
        ServiceProxy invocationHandler = null;
        if(cls.isInterface()){
            throw new RuntimeException("需要具体实现类类型");
        }
        Interfaces = cls.getInterfaces();
        if(Interfaces == null || Interfaces.length < 1){
            throw new RuntimeException("未实现任何接口");
        }
        try {
            invocationHandler = new ServiceProxy(cls.newInstance());
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
            throw new RuntimeException("创建代理失败");
        }
        proxyInstance = Proxy.newProxyInstance(  
                cls.getClassLoader(),  
                Interfaces, 
                invocationHandler); 
        return proxyInstance;
    }
    
}
