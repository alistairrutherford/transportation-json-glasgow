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

/**
 * Traffic data class.
 * 
 */
public class TrafficData
{
	// ------------------------------------------------------------------------
	// Constants
	// ------------------------------------------------------------------------
	public static final String TAG_SITUATION_RECORD = "situationRecord";
	public static final String TAG_ATTRIBUTES = "@attributes";
	
	public static final String TAG_ID = "id";
	public static final String TAG_NON_GENERAL_PUBLIC_COMMENT = "nonGeneralPublicComment";
	public static final String TAG_COMMENT = "comment";
	public static final String TAG_VALUE = "value";
	public static final String TAG_NETWORK_MANAGEMENT_TYPE = "networkManagementType";
	public static final String TAG_POINT_COORDINATES = "pointCoordinates";
	public static final String TAG_POINT = "point";
	public static final String TAG_NAME = "name";
	public static final String TAG_LATITUDE = "latitude";
	public static final String TAG_LONGITUDE = "longitude";
	public static final String TAG_TPEG_DESCRIPTOR_TYPE = "tpegDescriptorType";
	public static final String TAG_LOCAL_LINK_NAME = "localLinkName";
	public static final String TAG_LINK_NAME = "linkName";
	public static final String TAG_TOWN_NAME = "townName";
	public static final String TAG_OVERALL_START_TIME = "overallStartTime";
	public static final String TAG_OVERALL_END_TIME = "overallEndTime";
	
	public static final String TEXT_UNKNOWN = "n/a";
	
	private String id;
	private String description;
	private String localLinkName;
	private String linkName;
	private String townName;
	private String type;
	private String latitude;
	private String longitude;
	private String overallStartTime;
	private String overallEndTime;
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getLatitude()
	{
		return latitude;
	}
	
	public void setLatitude(String latitude)
	{
		this.latitude = latitude;
	}
	
	public String getLongitude()
	{
		return longitude;
	}
	
	public void setLongitude(String longitude)
	{
		this.longitude = longitude;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	
	public String getLocalLinkName()
	{
		return localLinkName;
	}

	public void setLocalLinkName(String localLinkName)
	{
		this.localLinkName = localLinkName;
	}

	public String getLinkName()
	{
		return linkName;
	}

	public void setLinkName(String linkName)
	{
		this.linkName = linkName;
	}

	public String getTownName()
	{
		return townName;
	}

	public void setTownName(String townName)
	{
		this.townName = townName;
	}

	public String getType()
	{
		return type;
	}
	
	public void setType(String type)
	{
		this.type = type;
	}
	
	public String getOverallStartTime()
	{
		return overallStartTime;
	}
	
	public void setOverallStartTime(String overallStartTime)
	{
		this.overallStartTime = overallStartTime;
	}
	
	public String getOverallEndTime()
	{
		return overallEndTime;
	}
	
	public void setOverallEndTime(String overallEndTime)
	{
		this.overallEndTime = overallEndTime;
	}
	
	@Override
	public String toString()
	{
		String text = id + ", " + description + ", " + localLinkName  + ", " + linkName  + ", " + townName  + ", " + latitude + ", " + longitude + "," + type + ", " + overallStartTime + ", " + overallEndTime;
		
		return text;
	}
}