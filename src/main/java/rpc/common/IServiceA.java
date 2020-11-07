package rpc.common;

import rpc.common.exception.ServiceMethodParamException;

public interface IServiceA {
    
    public Long sum(Integer[] ints) throws ServiceMethodParamException;
    
}
