/*
 * $Id: UnauthorizedActionException.java 421119 2006-07-12 04:49:11Z wsmoak $
 *
 * Copyright 2003-2005 The Apache Software Foundation.
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
package org.apache.struts.chain.commands;


/**
 * <p>Exception thrown when the chosen action mapping is not authorized for
 * the current request.</p>
 *
 * @version $Rev: 421119 $ $Date: 2005-06-04 10:58:46 -0400 (Sat, 04 Jun 2005)
 *          $
 */
public class UnauthorizedActionException extends Exception {
    /**
     * <p> Default no-argument constructor. </p>
     */
    public UnauthorizedActionException() {
        super();
    }

    /**
     * <p> Constructor to set message on instantiation. </p>
     *
     * @param message The error or warning message.
     */
    public UnauthorizedActionException(String message) {
        super(message);
    }
}
