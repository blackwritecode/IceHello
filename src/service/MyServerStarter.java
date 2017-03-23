package service;

public class MyServerStarter {

	public static void main(String[] args) {
		int status = 0;
		Ice.Communicator ic = null;
		try{
			/*
			 * 初始化 Communicator 对象，
			 * args 可以传一些初始化参数，
			 * 如连接超时，初始化客户端连接池的数量等。
			 */
			ic = Ice.Util.initialize(args);
			/*
			 * 创建名为 MySerrviceAdapter 的 ObjectAdapter，
			 * 使用缺省的通信协议（ TCP/IP 对口为10000 的请求。
			 * -- 服务端声明 adapter ， 客户端通过此访问。
			 */
			Ice.ObjectAdapter adapter = ic.createObjectAdapterWithEndpoints("MyServiceAdapter", "default -p 10001");
			// 实例化一个 MyService 服务对象 （Servant）
			// -- 业务实现对象
			MyServiceImpl servant = new MyServiceImpl();
			/*
			 * 将 Servant 增加到 ObjectAdatper 中，并将 Servant 关联到 ID 为 MyService 的Ice Object 
			 * -- 将业务实现对象与 adapter关联
			 */
			adapter.add(servant, Ice.Util.stringToIdentity("MyService"));
			// 激活 ObjectAdapter
			adapter.activate();
			// 让服务退出之前，一直保持对请求的监听
			System.out.println("Server started");
			ic.waitForShutdown();
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
