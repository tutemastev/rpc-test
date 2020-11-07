package rpc.service;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ServiceProxy implements InvocationHandler {
    
    private Object obj;
    
    public ServiceProxy(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        // TODO service invoke
        result = method.invoke(obj, args);
        return result;
    }
    
}
