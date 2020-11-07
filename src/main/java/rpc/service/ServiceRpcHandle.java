package rpc.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import rpc.common.IServiceA;
import rpc.common.RequestBeanDTO;
import rpc.common.ResponseBeanDTO;

public class ServiceRpcHandle {
	private static Map<String, Class<?>> mapCls;
	static {
		mapCls =  new HashMap<String, Class<?>>();
		mapCls.put(IServiceA.class.getName(), ServiceA.class);
	}

	public static ResponseBeanDTO runRpcSericeMethod(RequestBeanDTO requestDTO) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		ResponseBeanDTO ResponseBeanDTO = new ResponseBeanDTO();
		String interfaceName = requestDTO.getInterfaceName();
		String methodName = requestDTO.getMethodName();
		//String methodReturnClassName = requestDTO.getMethodReturnClassName();
		Object[] data = (Object[]) requestDTO.getData();
		//Object obj = ServiceProxyUtil.getProxyInstance(ServiceStart.mapCls.get(Class.forName(interfaceName)));
		//Class<?> interfaceCls = Class.forName(interfaceName);
		Class<?> implServiceCls = mapCls.get(interfaceName);
		Method[] methos = implServiceCls.getDeclaredMethods();
		for (Method method : methos) {
			if(method.getName().equals(methodName)){
				Object resultMethod = method.invoke(implServiceCls.newInstance(), data);
				ResponseBeanDTO.setData(resultMethod);
				System.out.println(resultMethod.toString());
				break;
			}
		}
		return ResponseBeanDTO;
	}
	
}
