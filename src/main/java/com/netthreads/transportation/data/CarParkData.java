/**
 * -----------------------------------------------------------------------
 * Copyright 2013 - Alistair Rutherford - www.netthreads.co.uk
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
package com.netthreads.transportation.data;

import java.io.Serializable;

/**
 * Car Park data class.
 * 
 */
public class CarParkData implements Serializable
{
	/**
	 * 
	 */
    private static final long serialVersionUID = -4305130932635634314L;
    
	// ------------------------------------------------------------------------
	// Constants
	// ------------------------------------------------------------------------
	public static final String TEXT_ID = "id";
	public static final String TAG_ATTRIBUTES = "@attributes";
	
	public static final String TAG_SITUATION_RECORD = "situationRecord";
	public static final String TAG_ID = "id";
	public static final String TAG_POINT_COORDINATES = "pointCoordinates";
	
	public static final String TAG_LATITUDE = "latitude";
	public static final String TAG_LONGITUDE = "longitude";
	public static final String TAG_CAR_PARK_IDENTITY = "carParkIdentity";
	public static final String TAG_CAR_PARK_OCCUPANCY = "carParkOccupancy";
	public static final String TAG_CAR_PARK_STATUS = "carParkStatus";
	public static final String TAG_OCCUPIED_SPACES = "occupiedSpaces";
	public static final String TAG_TOTAL_CAPACITY = "totalCapacity";
	
	private String id;
	private String carParkIdentity;
	private String carParkOccupancy;
	private String carParkStatus;
	private String occupiedSpaces;
	private String latitude;
	private String longitude;
	private String totalCapacity;
	
	public String getId()
	{
		return id;
	}
	
	public void setId(String id)
	{
		this.id = id;
	}
	
	public String getCarParkIdentity()
	{
		return carParkIdentity;
	}
	
	public void setCarParkIdentity(String carParkIdentity)
	{
		this.carParkIdentity = carParkIdentity;
	}
	
	public String getCarParkOccupancy()
	{
		return carParkOccupancy;
	}
	
	public void setCarParkOccupancy(String carParkOccupancy)
	{
		this.carParkOccupancy = carParkOccupancy;
	}
	
	public String getCarParkStatus()
	{
		return carParkStatus;
	}
	
	public void setCarParkStatus(String carParkStatus)
	{
		this.carParkStatus = carParkStatus;
	}
	
	public String getOccupiedSpaces()
	{
		return occupiedSpaces;
	}
	
	public void setOccupiedSpaces(String occupiedSpaces)
	{
		this.occupiedSpaces = occupiedSpaces;
	}
	
	public String getTotalCapacity()
	{
		return totalCapacity;
	}
	
	public void setTotalCapacity(String totalCapacity)
	{
		this.totalCapacity = totalCapacity;
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
	
	@Override
	public String toString()
	{
		String text = id + ", " + carParkIdentity + ", " + latitude + ", " + longitude + "," + carParkOccupancy + ", " + carParkStatus + ", " + occupiedSpaces + ", " + totalCapacity;
		
		return text;
	}
}