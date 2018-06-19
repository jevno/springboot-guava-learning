package com.guava;

import static java.util.stream.Collectors.joining;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.google.common.base.Joiner;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Files;

/**
 * 
 * @title 		Guava JoinerTest 字符串拼接
 * @description	源码分析文章https://blog.jamespan.me/2015/02/08/guava-basic-utilities-1
 * @usage		
 * @copyright	Copyright 2017  marsmob Corporation. All rights reserved.
 * @company		marsmob
 * @author		jevno
 * @create		2018年6月14日下午7:04:43
 */
public class JoinerTest {
	
	private final List<String> stringList = Arrays.asList("Google", "Guava", "Java", "Scala", "Groovy");
	
	private final List<String> stringListWithNullValue = Arrays.asList("Google", "Guava", "Java", "Scala", null);
	
	private final String targetFileName = "D:\\stswork\\github\\springboot-guava-learning\\guava-joiner.txt";
	
	private final String targetFileNameToMap = "D:\\stswork\\github\\springboot-guava-learning\\guava-joiner-map.txt";
	
	private final Map<String,Object> stringMap = ImmutableMap.of("name","jevno","age", 28);
			
	
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
	
	/**
	* @Title: testJoinOnJoinWithNullValueButSkip
	* @Description: DO(忽略空值)
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	@Test
	public void testJoinOnJoinWithNullValueButSkip()
	{
		String result = Joiner.on("#").skipNulls().join(stringListWithNullValue);
		assertThat(result, equalTo("Google#Guava#Java#Scala"));
	}
	
	/**
	 * 
	* @Title: testJoin_On_Join_WithNullValue_UseDefaultValue
	* @Description: DO(对null进行替换操作)
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	@Test
	public void testJoin_On_Join_WithNullValue_UseDefaultValue()
	{
		String result = Joiner.on("#").useForNull("DEFAULT").join(stringListWithNullValue);
		assertThat(result, equalTo("Google#Guava#Java#Scala#DEFAULT"));
	}
	
	/**
	 * 
	* @Title: testJoin_On_Append_To_StringBuilder
	* @Description: DO(动态拼接)
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	@Test
	public void testJoin_On_Append_To_StringBuilder()
	{
		final StringBuilder builder = new StringBuilder();
		StringBuilder resultBuilder = Joiner.on("#").useForNull("DEFAULT").appendTo(builder, stringListWithNullValue);
		assertThat(resultBuilder, sameInstance(builder));
		assertThat(resultBuilder.toString(), equalTo("Google#Guava#Java#Scala#DEFAULT"));
		assertThat(builder.toString(), equalTo("Google#Guava#Java#Scala#DEFAULT"));
	}
	
	/**
	 * 
	* @Title: testJoin_On_Append_To_Writer
	* @Description: DO(appendTo 文件写入)
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	@Test
	public void testJoin_On_Append_To_Writer()
	{
		try(FileWriter writer = new FileWriter(new File(targetFileName)))
		{
			Joiner.on("#").useForNull("DEFAULT").appendTo(writer, stringListWithNullValue);
			assertThat(Files.isFile().test(new File(targetFileName)), equalTo(true));
		}catch (IOException e) {
			fail("append to the writer occur fetal error.");
		};
	}
	
	/**
	* @Title: testJoiningByStreamSkipNullValues
	* @Description: DO(jdk8 join)
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	@Test
	public void testJoiningByStreamSkipNullValues()
	{
		String result = stringListWithNullValue.stream().filter(item -> item != null && !item.isEmpty()).collect(joining("#"));
		assertThat(result, equalTo("Google#Guava#Java#Scala"));
	}
	
	/**
	* @Title: testJoiningByStreamWithDefaultValue
	* @Description: DO(jdk8 join with default)
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	@Test
	public void testJoiningByStreamWithDefaultValue()
	{
		String result = stringListWithNullValue.stream()
							.map(item -> item==null || item.isEmpty() ? "DEFAULT" : item)
							.collect(joining("#"));
		assertThat(result, equalTo("Google#Guava#Java#Scala#DEFAULT"));
	}
	
	@Test
	public void testJoinOnMapJoin()
	{
		String result = Joiner.on(";").withKeyValueSeparator("=").join(stringMap);
		assertThat(result, equalTo("name=jevno;age=28"));
	}
	
	@Test
	public void testJoinOnMapJoinToAppendable()
	{
		try(FileWriter writer = new FileWriter(new File(targetFileNameToMap)))
		{
			Joiner.on(";").withKeyValueSeparator("=").appendTo(writer, stringMap);
			assertThat(Files.isFile().test(new File(targetFileNameToMap)), equalTo(true));
		}catch (IOException e) {
			fail("append to the writer occur fetal error.");
		};
	}
}
