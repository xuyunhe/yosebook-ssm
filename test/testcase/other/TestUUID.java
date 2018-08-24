package testcase.other;

import java.util.UUID;

/**
* @author 作者程万里 E-mail1273919421@:
* @version 创建时间：2018年5月1日 下午8:04:48
* 类说明：侵权必究。。。。。。。
*/

public class TestUUID {
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString().replace("-", "").toUpperCase());
		// TODO Auto-generated method stub

	}

}
