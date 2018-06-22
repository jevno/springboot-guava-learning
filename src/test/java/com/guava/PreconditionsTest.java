package com.guava;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.google.common.base.Preconditions;

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
	
	@Test
	public void testCheckNotNullWithMessage()
	{
		try {
			checkNotNullWithMessage(null);
		} catch (Exception e) {
//			assertThat(e, is(NullPointerException.class));
			assertThat(e.getMessage(), equalTo("The list should not be null"));
		}
	}
	
	@Test
	public void testCheckNotNullWithFormatMessage()
	{
		try {
			checkNotNullWithFormatMessage(null);
		} catch (Exception e) {
//			assertThat(e, is(NullPointerException.class));
			assertThat(e.getMessage(), equalTo("The list should not be null and the size must be 2"));
		}
	}
	
	@Test
	public void testCheckArguments()
	{
		final String type = "A";
		
		try {
			Preconditions.checkArgument(type.equals("B"));
		} catch (Exception e) {
			//assertThat(e, is(IllegalArgumentException.class));
		}
	}
	
	private void checkNotNull(final List<String> list)
	{
		Preconditions.checkNotNull(list);
	}
	
	private void checkNotNullWithMessage(final List<String> list)
	{
		Preconditions.checkNotNull(list, "The list should not be null");
	}
	
	private void checkNotNullWithFormatMessage(final List<String> list)
	{
		Preconditions.checkNotNull(list, "The list should not be null and the size must be %s", 2);
	}
}
