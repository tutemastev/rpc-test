package rpc.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

import rpc.common.CommonStaticSource;

public class ClientSocket {
	private static final String IP_ADD = "127.0.0.1";
	//private static final int CLIENT_SOCKET_TIMEOUT = 5000;
	private static Socket socket = null;
	/*private static ObjectOutputStream objectOutputStream;
	private static ObjectInputStream objectInputStream;*/
	static {
		/*try {
			socket = new Socket(IP_ADD, CommonStaticSource.PROT);
			objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			objectInputStream = new ObjectInputStream(socket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}*/
	}

	public static Socket getSocket(){
		return socket;
		
	}
	
	public static synchronized Object oneRpcSericeMethodRun(Serializable obj){
		try {
			//很奇怪 要每次 新建
			socket = new Socket(IP_ADD, CommonStaticSource.PROT);
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
			ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
			objectOutputStream.writeObject(obj);
			objectOutputStream.flush();
			socket.getOutputStream().flush();
			int c = 0;
			while(true){
				if(c>5){
					throw new RuntimeException("处理超时");
				}
				try {
					TimeUnit.SECONDS.sleep(1);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				c++;
				try {
					Object result = objectInputStream.readObject();
					System.out.println("the rpc result is:"+ result.toString());
					if(result != null){
						return result;
					}
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					throw new RuntimeException("处理错误");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new RuntimeException("系统错误");
	}
	
	public static void shutDownClientSocket(){
		if(socket != null){
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
