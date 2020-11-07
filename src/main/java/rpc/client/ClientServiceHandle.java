package rpc.client;

import rpc.common.RequestBeanDTO;
import rpc.common.ResponseBeanDTO;

public class ClientServiceHandle {

    
	public static Object runRpcSericeMethod(Class<?> cls, RequestBeanDTO request) {
		Object t = null;
		String returnClassName = cls.getName();
		request.setMethodReturnClassName(returnClassName);
		ResponseBeanDTO responseBean;
		try {
			responseBean = ClientServiceHandle.rpcSendRequest(request);
			t = responseBean.getData();
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}
	}
	
	private static ResponseBeanDTO rpcSendRequest(RequestBeanDTO request) throws Exception{
		ResponseBeanDTO result = (ResponseBeanDTO) ClientSocket.oneRpcSericeMethodRun(request);
		System.out.println("--------client method result :"+result.getData()+"-----------");
		Exception rpcException = result.getException();
		if(rpcException != null){
			rpcException.printStackTrace();
			throw rpcException;
		}
		return result;
	}
	
    
}
