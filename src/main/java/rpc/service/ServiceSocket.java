package rpc.service;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import rpc.common.CommonStaticSource;
import rpc.common.IServiceA;
import rpc.common.RequestBeanDTO;
import rpc.common.ResponseBeanDTO;

public class ServiceSocket {
	public static Map<String, Class<?>> mapCls = new HashMap<String, Class<?>>();

	private static ServerSocket serverSocket = null;
	private static ThreadPoolExecutor threadPoolSocket = new ThreadPoolExecutor(4, 8, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(500), new ThreadPoolExecutor.CallerRunsPolicy());

	static{
		try {
			mapCls.put(IServiceA.class.getName(), ServiceA.class);
			serverSocket = new ServerSocket(CommonStaticSource.PROT);
			System.out.println("serverSocket start");
			ServiceSocket.getClientSocket();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void getClientSocket() throws IOException{
		while(true){
			Socket clientSocket = serverSocket.accept();
			System.out.println("一个客户端");
			handleMethodRequest(clientSocket);
		}
	}
	
	private static void handleMethodRequest(final Socket clientSocket){
		
		threadPoolSocket.execute(new Runnable() {
			@Override
			public void run() {
				OutputStream os = null;
				try {
					os = clientSocket.getOutputStream();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				ObjectOutputStream objectOutputStream = null;
				ObjectInputStream objectInputStream = null;
				ResponseBeanDTO response = new ResponseBeanDTO();
				try {
					objectOutputStream = new ObjectOutputStream(os);
					objectInputStream = new ObjectInputStream(clientSocket.getInputStream());
					int c = 0;
					while(true){
						if(c>3){
							response.setException(new RuntimeException("处理异常"));
							response.setResultCode("999");
							response.setResultMsg("failed");
							objectOutputStream.writeObject(response);
							objectOutputStream.flush();
							return;
						}
						try {
							TimeUnit.SECONDS.sleep(1);
						} catch (InterruptedException e) {
							e.printStackTrace();
							response.setException(e);
							objectOutputStream.writeObject(response);
							objectOutputStream.flush();
							return;
						}
						c++;
						try {
							Object request = objectInputStream.readObject();
							RequestBeanDTO requestDTO = null;
							if(request instanceof RequestBeanDTO){
								requestDTO = (RequestBeanDTO)request;
							} else {
								response.setException(new RuntimeException("请求参数错误"));
								response.setResultCode("999");
								response.setResultMsg("failed");
								objectOutputStream.writeObject(response);
								return;
							}
							if(requestDTO != null){
								response = ServiceRpcHandle.runRpcSericeMethod(requestDTO);
								response.setResultCode("001");
								response.setResultMsg("successful");
								System.out.println("-----"+response.getData().toString()+"-----");
								objectOutputStream.writeObject(response);
								objectOutputStream.flush();
								return;
							}
						} catch (ClassNotFoundException e) {
							e.printStackTrace();
							response.setException(new RuntimeException("处理异常"));
							response.setResultCode("999");
							response.setResultMsg("failed");
							objectOutputStream.writeObject(response);
							objectOutputStream.flush();
							return;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
					response.setException(new RuntimeException("处理异常"));
					response.setResultCode("999");
					response.setResultMsg("failed");
					try {
						objectOutputStream.writeObject(response);
						objectOutputStream.flush();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				} finally {
					try {
						objectOutputStream.close();
						objectInputStream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}
	
}
