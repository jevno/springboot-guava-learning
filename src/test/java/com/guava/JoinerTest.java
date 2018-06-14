package com.guava;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.google.common.base.Joiner;

/**
 * 
 * @title 		Guava JoinerTest
 * @description	
 * @usage		
 * @copyright	Copyright 2017  marsmob Corporation. All rights reserved.
 * @company		marsmob
 * @author		jevno
 * @create		2018年6月14日下午7:04:43
 */
public class JoinerTest {
	
	private final List<String> stringList = Arrays.asList("Google", "Guava", "Java", "Scala", "Groovy");
	
	private final List<String> stringListWithNullValue = Arrays.asList("Google", "Guava", "Java", "Scala", null);
	
	@Test
	public void testJoinOnJoin()
	{
		String result = Joiner.on("#").join(stringList);
		assertThat(result, equalTo("Google#Guava#Java#Scala#Groovy"));
	}
	
	@Test(expected = NullPointerException.class)
	public void testJoinOnJoinWithNullValue()
	{
		String result = Joiner.on("#").join(stringListWithNullValue);
		assertThat(result, equalTo("Google#Guava#Java#Scala#Groovy"));
	}
	
	@Test
	public void testJoinOnJoinWithNullValueButSkip()
	{
		String result = Joiner.on("#").skipNulls().join(stringListWithNullValue);
		assertThat(result, equalTo("Google#Guava#Java#Scala"));
	}
	
	@Test
	public void testJoin_On_Join_WithNullValue_UseDefaultValue()
	{
		String result = Joiner.on("#").useForNull("DEFAULT").join(stringListWithNullValue);
		assertThat(result, equalTo("Google#Guava#Java#Scala#DEFAULT"));
	}
}
