package rpc.client;

import rpc.common.exception.ServiceMethodParamException;

public class ClientStart {

    public static void main(String[] args) throws ServiceMethodParamException {
        // TODO ioc context 进行获取
        long result = new ExampleTestController().countIntsSum(new Integer[]{1, 10, 100});
        System.out.println(result);
    }

}
