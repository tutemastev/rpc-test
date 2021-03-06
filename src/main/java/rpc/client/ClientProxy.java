package rpc.client;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import rpc.common.RequestBeanDTO;

public class ClientProxy implements InvocationHandler {
	
	private ClientProxy() {
	}
	
	private static final ClientProxy INVOCATION_HANDLER = new ClientProxy();
	
	public static ClientProxy getClientProxy(){
		return INVOCATION_HANDLER;
	}
	
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        result = run(method, args);
        return result;
    }
    
    private Object run(Method method, Object[] args){
        Object result = 999L;//先默认写死 客户端执行
        String interfaceName = method.getDeclaringClass().getName().toString();
        String methodName = method.getName();
        //Class<?> methodReturnCls = method.getReturnType();
        RequestBeanDTO request = new RequestBeanDTO(methodName, interfaceName, args);
        result = ClientServiceHandle.runRpcSericeMethod(request);
        return result;
    }

}
