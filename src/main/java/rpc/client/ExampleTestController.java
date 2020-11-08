package rpc.client;

import rpc.common.IServiceA;
import rpc.common.exception.ServiceMethodParamException;

/**
 * @author zz.youpin.G_Y
 *         2018年4月20日
 *         说明:可以用spring ioc 进行管理 待优化
 */
public class ExampleTestController {

    private IServiceA iServiceA;

    {
    	//代理
    	iServiceA = (IServiceA) ClientProxyUtil.getProxyInstance(IServiceA.class);
    }

    public long countIntsSum(Integer[] ints) throws ServiceMethodParamException {
        return iServiceA.sum(ints);
    }

}
