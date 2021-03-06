package rpc.service;

import rpc.common.IServiceA;
import rpc.common.exception.ServiceMethodParamException;

public class ServiceA implements IServiceA {

    @Override
    public Long sum(Integer[] ints) throws ServiceMethodParamException {
        Long sum = 0L;
        if(ints==null){
            throw new ServiceMethodParamException("参数不合法");
        }
        for (int i : ints) {
            sum += i;
        }
        return sum;
    }
    
}
