package rpc.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import rpc.common.exception.ServiceMethodParamException;

public class ClientStart {
	
	private static ThreadPoolExecutor threadPoolSocket = new ThreadPoolExecutor(4, 8, 3, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(500), new ThreadPoolExecutor.CallerRunsPolicy());
	static {
		try {
			Class.forName("rpc.client.ClientSocket", true, Thread.currentThread().getContextClassLoader());
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

    public static void main(String[] args) throws ServiceMethodParamException {
    	final CountDownLatch count;
    	List<Integer[]> intsList = new ArrayList<Integer[]>();
    	intsList.add(new Integer[]{1,1,1});
    	intsList.add(new Integer[]{1,100,1});
    	intsList.add(new Integer[]{1,1,1212});
    	intsList.add(new Integer[]{1,888,1});
    	intsList.add(new Integer[]{1,1,999});
    	count = new CountDownLatch(intsList.size());
    	
    	final ExampleTestController controller = new ExampleTestController();
    	
    	for (final Integer[] integers : intsList) {
    		threadPoolSocket.execute(new Runnable() {
    			@Override
    			public void run() {
    				try {
    					long result = controller.countIntsSum(integers);
    					System.out.println(toString1(integers)+"-----sum result is -----"+result+"-----");
						
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						count.countDown();
					}
    				
    			}
    		});
		}
    	while(true){
    		try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		if(count.getCount()<1){
    			break;
    		}
    	}
    	ClientSocket.shutDownClientSocket();
    	threadPoolSocket.shutdown();
    }
    
    private static String toString1(Integer[] ints) {
    	String s = "";
    	for (Integer integer : ints) {
			s += "+"+integer;
		}
    	return s;
    }

}
