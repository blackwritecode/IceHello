package service;

import com.my.demo.demo.MyServicePrx;
import com.my.demo.demo.MyServicePrxHelper;

/**
 * MyService 客户端
 * @author pkls
 *
 */
public class MyServiceClient {

	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		try{
			// 初始化通信器
			ic = Ice.Util.initialize(args);
			// 传入远程服务单元的名称、网络协议、IP 及端口，构造一个 Proxy 对象
			// -- 服务单元名称是服务端 Identity
			Ice.ObjectPrx base = ic.stringToProxy("MyService:default -p 10001");
			/*
			 * 通过 checkedCase 向下转型，获取 MyService 接口的远程，
			 * 并同时检测根据传入的名称获取服务单元是否 MyService 的代理接口
			 */
			MyServicePrx proxy = MyServicePrxHelper.checkedCast(base);
			if(proxy == null){
				System.out.println("获取接口失败！");
				throw new Error("Invalid proxy");
			}
			// 调用服务方法
			String str = proxy.hellow();
			System.out.println("服务端返回值：" + str);
		}catch(Exception e){
			e.printStackTrace();
			status = 1;
		}finally{
			if(ic != null){
				ic.destroy();
			}
		}
		System.exit(status);
	}
}
