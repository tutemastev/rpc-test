package rpc.service;


public class ServiceStart {
	
	static {
		try {
			Class.forName("rpc.service.ServiceSocket", true, Thread.currentThread().getContextClassLoader());
			Class.forName("rpc.service.ServiceRpcHandle", true, Thread.currentThread().getContextClassLoader());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

    public static void main(String[] args) {
    	System.out.println("rpc service started!");
    }
    
}
