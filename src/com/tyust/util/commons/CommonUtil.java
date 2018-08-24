package com.tyust.util.commons;

import java.util.UUID;

public class CommonUtil {
	/*
	 * 用UUID产生随机字符串不会重复 用其来产生ID是一个很好的选择
	 */
	public static String uuid() {
		return UUID.randomUUID().toString().replace("-", "").toUpperCase();
	}

	
}
