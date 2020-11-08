package rpc.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import rpc.common.IServiceA;
import rpc.common.RequestBeanDTO;
import rpc.common.ResponseBeanDTO;

public class ServiceRpcHandle {
	private static Map<String, Object> mapCls;
	static {
		mapCls =  new HashMap<String, Object>();
		try {
			mapCls.put(IServiceA.class.getName(), ServiceA.class.newInstance());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ResponseBeanDTO runRpcSericeMethod(RequestBeanDTO requestDTO) throws ClassNotFoundException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, InstantiationException{
		ResponseBeanDTO ResponseBeanDTO = new ResponseBeanDTO();
		String interfaceName = requestDTO.getInterfaceName();
		String methodName = requestDTO.getMethodName();
		Object[] data = (Object[]) requestDTO.getData();
		Method[] methos = Class.forName(interfaceName).getDeclaredMethods();
		for (Method method : methos) {
			if(method.getName().equals(methodName)){
				Object resultMethod = method.invoke(mapCls.get(interfaceName), data);
				ResponseBeanDTO.setData(resultMethod);
				System.out.println(resultMethod.toString());
				break;
			}
		}
		return ResponseBeanDTO;
	}
	
}
