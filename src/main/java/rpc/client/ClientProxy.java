package rpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class ClientProxy implements InvocationHandler {

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // TODO client invoke
        Object result = null;
        result = run(method, args);
        return result;
    }
    
    private Object run(Method method, Object[] args){
        // TODO client run
        Object result = 999L;//先默认写死 客户端执行
        String interfaceName = method.getDeclaringClass().getName().toString();
        System.out.println(method.getName());
        System.out.println(args);
        System.out.println(interfaceName);
        return result;
    }

}
