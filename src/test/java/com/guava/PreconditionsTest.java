package com.guava;

import static com.google.common.base.Preconditions.checkNotNull;

import org.junit.Test;

/**
 * @title 		Preconditions Test 前置判断（断言）
 * @description	
 * @usage		
 * @copyright	Copyright 2017  marsmob Corporation. All rights reserved.
 * @company		marsmob
 * @author		jevno
 * @create		2018年6月19日下午2:16:12
 */
public class PreconditionsTest {

	@Test(expected = NullPointerException.class)
	public void testCheckNotNull()
	{
		checkNotNull(null);
	}
	
	
}
