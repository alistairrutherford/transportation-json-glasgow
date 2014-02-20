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

import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;

/**
 * JSON parser interface.
 * 
 */
public interface JSONParser<T>
{
	/**
	 * Process start of tag.
	 * 
	 * @param tag
	 * 
	 * @return True if data extracted.
	 */
	public boolean processStartObject(String tag, JsonParser parser);

	/**
	 * Process end of tag.
	 * 
	 * @param tag
	 * 
	 * @return True if data extracted.
	 */
	public boolean processEndObject(String tag, JsonParser parser);

	/**
	 * Process text in tag.
	 * 
	 * @param text
	 * @throws IOException 
	 * @throws JsonParseException 
	 */
	public void processObject(JsonParser parser) throws JsonParseException, IOException;

	/**
	 * Populate a record with data read.
	 * 
	 * @param data
	 */
	public void populateRecord(T data);

	/**
	 * Reset parser data.
	 * 
	 */
	public void reset();

	/**
	 * Within a target tag.
	 * 
	 * @return True if processing a tag.
	 */
	public boolean inTarget();
}
