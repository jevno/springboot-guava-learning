package com.guava;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import org.junit.Test;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

/**
 * 
 * @title 		Splitter Test 字符串截取
 * @description	源码分析文章https://blog.jamespan.me/2015/02/09/guava-basic-utilities-2
 * @usage		
 * @copyright	Copyright 2017  marsmob Corporation. All rights reserved.
 * @company		marsmob
 * @author		jevno
 * @create		2018年6月15日下午6:21:56
 */
public class SplitterTest {

	@Test
	public void testSplitOnSplit()
	{
		List<String> result = Splitter.on("|").splitToList("hello|world");
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(2));
		assertThat(result.get(0), equalTo("hello"));
		assertThat(result.get(1), equalTo("world"));
		
		String[] split = "hello|world|||".split("\\|"); // java内置的会忽略空内容
		assertThat(split, equalTo(Lists.newArrayList("hello", "world").toArray()));
		
		split = "hello|world|||".split("[|]"); // java内置的会忽略空内容
		assertThat(split, equalTo(Lists.newArrayList("hello", "world").toArray()));
		
		split = "hello|world|||".split(Pattern.quote("|")); // java内置的会忽略空内容
		assertThat(split, equalTo(Lists.newArrayList("hello", "world").toArray()));
	}
	
	/**
	* @Title: testSplit_On_Split_OmitEmpty
	* @Description: DO(使用omitEmptyStrings忽略空字符串)
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	@Test
	public void testSplit_On_Split_OmitEmpty()
	{
		List<String> result = Splitter.on("|").splitToList("hello|world|||");
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(5));
		result = Splitter.on("|").omitEmptyStrings().splitToList("hello|world|||");
		assertThat(result.size(), equalTo(2));
	}
	
	/**
	* @Title: testSplit_On_Split_OmitEmpty_TrimResult
	* @Description: DO(使用trimResults去除分割结果的前后空格)
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	@Test
	public void testSplit_On_Split_OmitEmpty_TrimResult()
	{
		List<String> result = Splitter.on("|").omitEmptyStrings().splitToList("hello | world|||");
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(2));
		
		result = Splitter.on("|").trimResults().omitEmptyStrings().splitToList("hello | world|||");
		assertThat(result.get(0), equalTo("hello"));
		assertThat(result.get(1), equalTo("world"));
	}
	
	/**
	* @Title: testSplitFixedLength
	* @Description: (使用fixedLength按照固定长度截取)
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	@Test
	public void testSplitFixedLength()
	{
		List<String> result = Splitter.fixedLength(4).splitToList("aaaabbbbccccdddd");
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(4));
		assertThat(result.get(0), equalTo("aaaa"));
		assertThat(result.get(1), equalTo("bbbb"));
	}
	
	/**
	* @Title: testSplitOnSplitLimit
	* @Description: DO(当分割的子字符串达到了limit个时，则停止分割)
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	@Test
	public void testSplitOnSplitLimit()
	{
		List<String> result = Splitter.on("#").limit(3).splitToList("aaaa#bbbb#cccc#dddd#eeee");
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(3));
		assertThat(result.get(0), equalTo("aaaa"));
		assertThat(result.get(2), equalTo("cccc#dddd#eeee"));
	}
	
	/**
	* @Title: testSplitonPatternString
	* @Description: DO(正则表达式分割)
	* @param     参数
	* @return void    返回类型
	* @throws
	 */
	@Test
	public void testSplitOnPatternString()
	{
		List<String> result = Splitter.onPattern("\\|").omitEmptyStrings().splitToList("hello|world|||");
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(2));
		assertThat(result.get(0), equalTo("hello"));
		assertThat(result.get(1), equalTo("world"));
	}
	
	@Test
	public void testSplitOnPattern()
	{
		List<String> result = Splitter.on(Pattern.compile("\\|")).omitEmptyStrings().splitToList("hello|world|||");
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(2));
		assertThat(result.get(0), equalTo("hello"));
		assertThat(result.get(1), equalTo("world"));
	}
	
	@Test
	public void testSplitOnSplitToMap()
	{
		Map<String,String> result = Splitter.on(Pattern.compile("\\|")).omitEmptyStrings().trimResults()
											.withKeyValueSeparator("=").split("hello=HELLO|world=WORLD|||");
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(2));
		assertThat(result.get("hello"), equalTo("HELLO"));
		assertThat(result.get("world"), equalTo("WORLD"));
		
	}
	
}
