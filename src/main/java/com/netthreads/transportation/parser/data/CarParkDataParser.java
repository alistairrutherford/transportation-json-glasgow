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
package com.netthreads.transportation.parser.data;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;

import com.netthreads.transportation.data.CarParkData;
import com.netthreads.transportation.parser.JSONParser;

/**
 * Type: Car park Data. Method: JSON
 * 
 */
public class CarParkDataParser implements JSONParser<CarParkData>
{
	// Record values.
	private String id;
	private String latitude;
	private String longitude;
	private String carParkIdentity;
	private String carParkOccupancy;
	private String carParkStatus;
	private String occupiedSpaces;
	private String totalCapacity;
	
	/**
	 * Tag Map.
	 */
	@SuppressWarnings("serial")
	private Map<String, Boolean> inTagMap = new HashMap<String, Boolean>()
	{
		{
			put(CarParkData.TAG_SITUATION_RECORD, false);
			put(CarParkData.TAG_ATTRIBUTES, false);
			put(CarParkData.TAG_POINT_COORDINATES, false);
		}
	};
	
	/**
	 * Construct parser.
	 * 
	 */
	public CarParkDataParser()
	{
		reset();
	}
	
	/**
	 * Process start tag
	 * 
	 * @param tag
	 * 
	 * @return True if targe tag found.
	 */
	@Override
	public boolean processStartObject(String tag, JsonParser parser)
	{
		boolean status = inTagMap.containsKey(tag);
		
		if (status)
		{
			inTagMap.put(tag, true);
		}
		
		return status;
	}
	
	/**
	 * Process end tag
	 * 
	 * @param tag
	 */
	@Override
	public boolean processEndObject(String tag, JsonParser parser)
	{
		boolean ready = false;
		
		Boolean inSituationRecord = inTagMap.get(CarParkData.TAG_SITUATION_RECORD);
		
		if (inSituationRecord != null)
		{
			if (tag.equals(CarParkData.TAG_SITUATION_RECORD))
			{
				ready = true;
			}
			
			if (inTagMap.containsKey(tag))
			{
				inTagMap.put(tag, false);
			}
		}
		
		return ready;
	}
	
	/**
	 * Collect text values depending on conditions.
	 * 
	 * @param text
	 * @throws IOException
	 * @throws JsonParseException
	 */
	@Override
	public void processObject(JsonParser parser) throws JsonParseException, IOException
	{
		// Old way was easier to understand.
		boolean inSituationRecord = inTagMap.get(CarParkData.TAG_SITUATION_RECORD);
		
		String text = parser.getText();
		
		if (inSituationRecord)
		{
			boolean inAttributes = inTagMap.get(CarParkData.TAG_ATTRIBUTES);
			
			if (inAttributes)
			{
				if (text.equals(CarParkData.TAG_ID))
				{
					// Extract id
					parser.nextToken();
					id = parser.getText();
				}
			}
			else
			{
				boolean inPointCoordinates = inTagMap.get(CarParkData.TAG_POINT_COORDINATES);
				
				if (inPointCoordinates)
				{
					if (text.equals(CarParkData.TAG_LATITUDE))
					{
						parser.nextToken();
						latitude = parser.getText();
					}
					else if (text.equals(CarParkData.TAG_LONGITUDE))
					{
						parser.nextToken();
						longitude = parser.getText();
					}
				}
				else if (text.equals(CarParkData.TAG_CAR_PARK_IDENTITY))
				{
					parser.nextToken();
					carParkIdentity = parser.getText();
				}
				else if (text.equals(CarParkData.TAG_CAR_PARK_OCCUPANCY))
				{
					parser.nextToken();
					carParkOccupancy = parser.getText();
				}
				else if (text.equals(CarParkData.TAG_CAR_PARK_STATUS))
				{
					parser.nextToken();
					carParkStatus = parser.getText();
				}
				else if (text.equals(CarParkData.TAG_OCCUPIED_SPACES))
				{
					parser.nextToken();
					occupiedSpaces = parser.getText();
				}
				else if (text.equals(CarParkData.TAG_TOTAL_CAPACITY))
				{
					parser.nextToken();
					totalCapacity = parser.getText();
				}
				
			}
		}
	}
	
	/**
	 * Build record from parsed data.
	 * 
	 */
	@Override
	public void populateRecord(CarParkData record)
	{
		// Populate record.
		record.setId(id);
		record.setLatitude(latitude);
		record.setLongitude(longitude);
		record.setCarParkIdentity(carParkIdentity);
		record.setCarParkOccupancy(carParkOccupancy);
		record.setCarParkStatus(carParkStatus);
		record.setOccupiedSpaces(occupiedSpaces);
		record.setTotalCapacity(totalCapacity);
		
		// Reset parser fields.
		reset();
	}
	
	/**
	 * Reset parsed strings.
	 * 
	 */
	@Override
	public void reset()
	{
		id = "";
		latitude = "";
		longitude = "";
		carParkIdentity = "";
		carParkOccupancy = "";
		carParkStatus = "";
		occupiedSpaces = "";
		totalCapacity = "";
	}
	
	/**
	 * Inside tag
	 * 
	 * @return True if inside tag.
	 */
	@Override
	public boolean inTarget()
	{
		boolean state = false;
		Iterator<Boolean> iterator = inTagMap.values().iterator();
		while (!state && iterator.hasNext())
		{
			Boolean item = iterator.next();
			state |= item;
		}
		
		return state;
	}
	
}
