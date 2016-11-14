/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

package org.apache.carbondata.core.load;

import org.junit.Before;
import org.junit.Test;

public class LoadMetadataDetailsUnitTest {

  private LoadMetadataDetails loadMetadataDetails;

  @Before public void setup() {
    loadMetadataDetails = new LoadMetadataDetails();
  }

  /**
   * This method will test Hashcode which will return 31 if we don't set loadName.
   *
   * @throws Exception
   */

  @Test public void testHashCodeLoadNameNull() throws Exception {
    Integer data = loadMetadataDetails.hashCode();
    assert (data == 31);
  }

  @Test public void testHashCodeValueInLoadName() throws Exception {
    loadMetadataDetails.setLoadName("test");
    Integer data = loadMetadataDetails.hashCode();
    assert (data != 31);
  }

  @Test public void testEqualsObjectIsNotLoadMetadataDetails() throws Exception {
    Object obj = new Object();
    boolean result = loadMetadataDetails.equals(obj);
    assert (!result);
  }

  @Test public void testEqualsObjectIsNull() throws Exception {
    boolean result = loadMetadataDetails.equals(new Object());
    assert (!result);
  }

  @Test public void testEqualsObjectIsLoadMetadataDetailsWithoutLoadName() throws Exception {
    LoadMetadataDetails obj = new LoadMetadataDetails();
    boolean result = loadMetadataDetails.equals(obj);
    assert (result);
  }

  @Test public void testEqualsObjectIsLoadMetadataDetails() throws Exception {
    loadMetadataDetails.setLoadName("test");
    LoadMetadataDetails obj = new LoadMetadataDetails();
    boolean result = loadMetadataDetails.equals(obj);
    assert (!result);
  }

  @Test public void testEqualsObjectIsLoadMetadataDetailsLoadNameNull() throws Exception {
    LoadMetadataDetails obj = new LoadMetadataDetails();
    obj.setLoadName("test");
    boolean result = loadMetadataDetails.equals(obj);
    assert (!result);
  }

  @Test public void testEqualsObjectIsLoadMetadataDetailsLoadNameEqualsObjectLoadName()
      throws Exception {
    loadMetadataDetails.setLoadName("test");
    LoadMetadataDetails obj = new LoadMetadataDetails();
    obj.setLoadName("test");
    boolean result = loadMetadataDetails.equals(obj);
    assert (result);
  }

  @Test public void testGetTimeStampWithEmptyTimeStamp() throws Exception {
    loadMetadataDetails.setLoadStartTime("");
    Long result = loadMetadataDetails.getLoadStartTimeAsLong();
    assert (result == null);
  }

  @Test public void testGetTimeStampWithParserException() throws Exception {
    loadMetadataDetails.setLoadStartTime("00.00.00");
    Long result = loadMetadataDetails.getLoadStartTimeAsLong();
    assert (result == null);
  }

  @Test public void testGetTimeStampWithDate() throws Exception {
    String oldString = "01-01-2016 00:00:00";
    loadMetadataDetails.setLoadStartTime(oldString);
    Long result = loadMetadataDetails.getLoadStartTimeAsLong();
    assert (result == 1451586600000000L);
  }
}
