package service;

import com.my.demo.demo._MyServiceDisp;

import Ice.Current;

/**
 * Ice Object服务类
 * 具体的业务实现
 * @author pkls
 *
 */
public class MyServiceImpl extends _MyServiceDisp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3169602192267730741L;

	@Override
	public String hellow(Current __current) {
		return "Hello world!";
	}

}
