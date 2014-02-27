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
import org.codehaus.jackson.JsonToken;

import com.netthreads.transportation.data.TrafficData;
import com.netthreads.transportation.parser.JSONParser;

/**
 * Type: Traffic Data. Method: JSON
 * 
 */
public class TrafficDataParser implements JSONParser<TrafficData>
{
	// Record values.
	private String id;
	private String description;
	private String temp;
	private String localLinkName;
	private String linkName;
	private String townName;
	private String type;
	private String latitude;
	private String longitude;
	private String overallStartTime;
	private String overallEndTime;
	
	/**
	 * Tag Map.
	 */
	@SuppressWarnings("serial")
	private Map<String, Boolean> inTagMap = new HashMap<String, Boolean>()
	{
		{
			put(TrafficData.TAG_SITUATION_RECORD, false);
			put(TrafficData.TAG_ATTRIBUTES, false);
			put(TrafficData.TAG_POINT, false);
			put(TrafficData.TAG_POINT_COORDINATES, false);
			put(TrafficData.TAG_NON_GENERAL_PUBLIC_COMMENT, false);
		}
	};
	
	/**
	 * Construct parser.
	 * 
	 */
	public TrafficDataParser()
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
		
		Boolean inSituationRecord = inTagMap.get(TrafficData.TAG_SITUATION_RECORD);
		
		if (inSituationRecord != null)
		{
			if (tag.equals(TrafficData.TAG_SITUATION_RECORD))
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
		boolean inSituationRecord = inTagMap.get(TrafficData.TAG_SITUATION_RECORD);
		
		String text = parser.getText();
		
		if (inSituationRecord)
		{
			boolean inAttributes = inTagMap.get(TrafficData.TAG_ATTRIBUTES);
			
			if (inAttributes)
			{
				if (text.equals(TrafficData.TAG_ID))
				{
					// Extract id
					parser.nextToken();
					id = parser.getText();
				}
			}
			else
			{
				boolean inPoint = inTagMap.get(TrafficData.TAG_POINT);
				
				if (inPoint)
				{
					boolean inPointCoordinates = inTagMap.get(TrafficData.TAG_POINT_COORDINATES);
					
					if (inPointCoordinates)
					{
						if (text.equals(TrafficData.TAG_LATITUDE))
						{
							parser.nextToken();
							latitude = parser.getText();
						}
						else if (text.equals(TrafficData.TAG_LONGITUDE))
						{
							parser.nextToken();
							longitude = parser.getText();
						}
					}
					else if (text.equals(TrafficData.TAG_NAME))
					{
						while (parser.nextToken() != JsonToken.END_ARRAY)
						{
							text = parser.getText();
							if (text.equals(TrafficData.TAG_VALUE))
							{
								parser.nextToken();
								temp = parser.getText();
							}
							else if (text.equals(TrafficData.TAG_TPEG_DESCRIPTOR_TYPE))
							{
								parser.nextToken();
								text = parser.getText();
								if (text.equals(TrafficData.TAG_LINK_NAME))
								{
									linkName = temp;
								}
								else if (text.equals(TrafficData.TAG_LOCAL_LINK_NAME))
								{
									localLinkName = temp;
								}
								else if (text.equals(TrafficData.TAG_TOWN_NAME))
								{
									townName = temp;
								}
							}
						}
					}
				}
				else
				{
					if (text.equals(TrafficData.TAG_NETWORK_MANAGEMENT_TYPE))
					{
						parser.nextToken();
						type = parser.getText();
					}
					else
					{
						if (text.equals(TrafficData.TAG_OVERALL_START_TIME))
						{
							parser.nextToken();
							overallStartTime = parser.getText();
						}
						else
						{
							if (text.equals(TrafficData.TAG_OVERALL_END_TIME))
							{
								parser.nextToken();
								overallEndTime = parser.getText();
							}
							else
							{
								boolean inNonGeneralPublicComment = inTagMap.get(TrafficData.TAG_NON_GENERAL_PUBLIC_COMMENT);
								
								if (inNonGeneralPublicComment)
								{
									if (text.equals(TrafficData.TAG_COMMENT))
									{
										parser.nextToken();
										parser.nextToken();
										parser.nextToken();
										description = parser.getText();
									}
								}
							}
							
						}
					}
				}
			}
		}
	}
	
	/**
	 * Build record from parsed data.
	 * 
	 */
	@Override
	public void populateRecord(TrafficData record)
	{
		// Populate record.
		record.setId(id);
		record.setDescription(description);
		record.setLinkName(linkName);
		record.setLocalLinkName(localLinkName);
		record.setTownName(townName);
		record.setType(type);
		record.setLatitude(latitude);
		record.setLongitude(longitude);
		record.setOverallStartTime(overallStartTime);
		record.setOverallEndTime(overallEndTime);
		
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
		description = "";
		localLinkName = "";
		linkName = "";
		townName = "";
		type = TrafficData.TEXT_UNKNOWN;
		latitude = "";
		longitude = "";
		overallStartTime = "";
		overallEndTime = "";
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
