package com.guava;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;

import com.google.common.base.Splitter;

/**
 * 
 * @title 		Splitter Test
 * @description	
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
	}
	
	@Test
	public void testSplit_On_Split_OmitEmpty()
	{
		List<String> result = Splitter.on("|").splitToList("hello|world|||");
		assertThat(result, notNullValue());
		assertThat(result.size(), equalTo(5));
		result = Splitter.on("|").omitEmptyStrings().splitToList("hello|world|||");
		assertThat(result.size(), equalTo(2));
	}
	
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
}
