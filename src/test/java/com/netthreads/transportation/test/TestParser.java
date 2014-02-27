/**
 * -----------------------------------------------------------------------
 * Copyright 2014 - Alistair Rutherford - www.netthreads.co.uk
 * -----------------------------------------------------------------------
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package com.netthreads.transportation.test;

import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import com.netthreads.transportation.data.CarParkData;
import com.netthreads.transportation.data.TrafficData;
import com.netthreads.transportation.parser.StreamParser;
import com.netthreads.transportation.parser.StreamParserImpl;
import com.netthreads.transportation.parser.data.CarParkDataFactory;
import com.netthreads.transportation.parser.data.CarParkDataParser;
import com.netthreads.transportation.parser.data.TrafficDataFactory;
import com.netthreads.transportation.parser.data.TrafficDataParser;

/**
 * Simple test to pull data items from Glasgow Traffic feed.
 * 
 */
public class TestParser
{
	private static final String JSON_TRAFFIC_FILE = "/trafficEvents.json";
	private static final String JSON_CARPARK_FILE = "/carParkData.json";
	
	/**
	 * Test Traffic Data.
	 * 
	 */
	@Test
	public void testReadTrafficData()
	{
		final InputStream entityStream = ClassLoader.class.getResourceAsStream(JSON_TRAFFIC_FILE);
		
		final List<TrafficData> list = new LinkedList<TrafficData>();
		final TrafficDataFactory dataFactory = new TrafficDataFactory();
		
		final StreamParser<TrafficData> streamParser = new StreamParserImpl<TrafficData>();
		
		streamParser.fetch(entityStream, list, dataFactory, new TrafficDataParser());
		
		org.junit.Assert.assertTrue(list.size() > 0);
		
		dumpTrafficDataResults(list);
	}
	
	/**
	 * Test Car Park Data.
	 * 
	 */
	@Test
	public void testReadCarParkData()
	{
		final InputStream entityStream = ClassLoader.class.getResourceAsStream(JSON_CARPARK_FILE);
		
		final List<CarParkData> list = new LinkedList<CarParkData>();
		final CarParkDataFactory dataFactory = new CarParkDataFactory();
		
		final StreamParser<CarParkData> streamParser = new StreamParserImpl<CarParkData>();
		
		streamParser.fetch(entityStream, list, dataFactory, new CarParkDataParser());
		
		org.junit.Assert.assertTrue(list.size() > 0);
		
		dumpCarParkDataResults(list);
	}
	
	/**
	 * Dump out results so we can take a look at them.
	 * 
	 * @param list
	 */
	private void dumpTrafficDataResults(final List<TrafficData> list)
	{
		for (TrafficData data : list)
		{
			System.out.println(data.toString());
		}
	}
	
	/**
	 * Dump out results so we can take a look at them.
	 * 
	 * @param list
	 */
	private void dumpCarParkDataResults(final List<CarParkData> list)
	{
		for (CarParkData data : list)
		{
			System.out.println(data.toString());
		}
	}
	
}
