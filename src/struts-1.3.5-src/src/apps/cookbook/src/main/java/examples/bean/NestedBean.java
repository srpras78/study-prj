/*
 * $Id: NestedBean.java 421486 2006-07-13 03:37:08Z wsmoak $
 *
 * Copyright 2005 The Apache Software Foundation.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package examples.bean;

import java.io.Serializable;


/**
 * Another Example Bean
 *
 * @version $Rev: 421486 $ $Date: 2006-07-12 20:37:08 -0700 (Wed, 12 Jul 2006) $
 */
public class NestedBean implements Serializable {

    // ------------------------------------------------------ Instance Variables

    /** A string value */
    private String stringValue = "This is a string from NestedBean";

    // ------------------------------------------------------------ Constructors

    /**
     * Constructor for NestedBean.
     */
    public NestedBean() {
        super();
    }

    // -------------------------------------------------------------- Properties


    /**
     * Returns the stringValue.
     * @return String
     */
    public String getStringValue() {
        return stringValue;
    }

    /**
     * Sets the stringValue.
     * @param stringValue The stringValue to set
     */
    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

 }
