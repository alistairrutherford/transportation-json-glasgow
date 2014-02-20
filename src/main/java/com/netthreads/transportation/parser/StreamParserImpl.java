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
package com.netthreads.transportation.parser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonToken;

/**
 * Stream parser implementation.
 * 
 */
public class StreamParserImpl<T> implements StreamParser<T>
{
	public static final String TEXT_LINK_DELIMETER = ";";
	public static final String TEXT_TITLE_DELIMETER = " ";
	
	private JsonFactory factory;
	private JsonParser parser;
	
	// Control
	private int state = WAITING;
	
	public StreamParserImpl()
	{
		factory = new JsonFactory();
	}
	
	/**
	 * Fetch and parse data.
	 * 
	 * @param stream
	 * @param list
	 * @param dataFactory
	 */
	@Override
	public int fetch(InputStream stream, List<T> list, DataFactory<T> dataFactory, JSONParser<T> pullParser)
	{
		state = BUSY;
		
		reset();
		
		try
		{
			parser = factory.createJsonParser(stream);
			
			JsonToken jsonToken = null;
			
			while ((jsonToken = parser.nextToken()) != null)
			{
				if (jsonToken.equals(JsonToken.START_OBJECT))
				{
					String tag = parser.getCurrentName();
					
					if (tag != null)
					{
						pullParser.processStartObject(tag, parser);
					}
				}
				else if (jsonToken.equals(JsonToken.END_OBJECT))
				{
					String tag = parser.getCurrentName();
					
					if (tag != null)
					{
						// Returns true when object complete.
						if (pullParser.processEndObject(tag, parser))
						{
							// Create holding record
							T data = dataFactory.createRecord();
							
							pullParser.populateRecord(data);
							
							list.add(data);
						}
					}
				}
				else
				{
					pullParser.processObject(parser);
				}
				
			}
		}
		catch (JsonParseException e)
		{
			// Oops
			state = ERROR;
		}
		catch (IOException e)
		{
			// Oops
			state = ERROR;
		}
		
		if (state != CANCELLED && state != ERROR)
		{
			state = DONE;
		}
		
		return state;
	}
	
	/**
	 * Stops the handler process.
	 * 
	 */
	@Override
	public void cancel()
	{
		state = CANCELLED;
	}
	
	/**
	 * Reset parser state.
	 * 
	 */
	@Override
	public void reset()
	{
		state = WAITING;
	}
	
	/**
	 * Return parser state code.
	 * 
	 */
	@Override
	public int getState()
	{
		return state;
	}
	
}
