/*
 * $Id: AbstractSetContentType.java 421119 2006-07-12 04:49:11Z wsmoak $
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

import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.config.ModuleConfig;

/**
 * <p>Check to see if the content type is set, and if so, set it for this
 * response.</p>
 *
 * @version $Rev: 421119 $ $Date: 2005-11-12 13:01:44 -0500 (Sat, 12 Nov 2005)
 *          $
 */
public abstract class AbstractSetContentType extends ActionCommandBase {
    // ---------------------------------------------------------- Public Methods

    /**
     * <p>Check to see if the content type is set, and if so, set it for this
     * response.</p>
     *
     * @param actionCtx The <code>Context</code> for the current request
     * @return <code>false</code> so that processing continues
     * @throws Exception if thrown by the Action class
     */
    public boolean execute(ActionContext actionCtx)
        throws Exception {
        // Retrieve the ModuleConfig instance
        ModuleConfig moduleConfig = actionCtx.getModuleConfig();

        // If the content type is configured, set it for the response
        String contentType =
            moduleConfig.getControllerConfig().getContentType();

        if (contentType != null) {
            setContentType(actionCtx, contentType);
        }

        return (false);
    }

    // ------------------------------------------------------- Protected Methods

    /**
     * <p>Request no cache flags are set.</p>
     *
     * @param context     The <code>Context</code> for this request
     * @param contentType The content type for the response
     */
    protected abstract void setContentType(ActionContext context,
        String contentType);
}
