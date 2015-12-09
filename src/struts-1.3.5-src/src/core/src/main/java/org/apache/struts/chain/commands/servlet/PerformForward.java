/*
 * Copyright 2003-2006 The Apache Software Foundation.
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
package org.apache.struts.chain.commands.servlet;

import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionServlet;
import org.apache.struts.chain.commands.AbstractPerformForward;
import org.apache.struts.chain.contexts.ActionContext;
import org.apache.struts.chain.contexts.ServletActionContext;
import org.apache.struts.config.ForwardConfig;
import org.apache.struts.config.ModuleConfig;
import org.apache.struts.util.MessageResources;
import org.apache.struts.util.RequestUtils;
import org.apache.struts.util.ModuleUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * <p>Perform forwarding or redirection based on the specified
 * <code>ForwardConfig</code> (if any).</p>
 *
 * @version $Rev: 421119 $ $Date: 2006-07-11 21:49:11 -0700 (Tue, 11 Jul 2006) $
 */
public class PerformForward extends AbstractPerformForward {
    private static final Log LOG = LogFactory.getLog(PerformForward.class);

    // ------------------------------------------------------- Protected Methods

    /**
     * <p>Perform the appropriate processing on the specified
     * <code>ForwardConfig</code>.</p>
     *
     * @param context       The context for this request
     * @param forwardConfig The forward to be performed
     */
    protected void perform(ActionContext context, ForwardConfig forwardConfig)
        throws Exception {
        ServletActionContext sacontext = (ServletActionContext) context;
        String uri = forwardConfig.getPath();

        if (uri == null) {
            ActionServlet servlet = sacontext.getActionServlet();
            MessageResources resources = servlet.getInternal();

            throw new IllegalArgumentException(resources.getMessage("forwardPathNull"));
        }

        HttpServletRequest request = sacontext.getRequest();
        ServletContext servletContext = sacontext.getContext();
        HttpServletResponse response = sacontext.getResponse();

        if (uri.startsWith("/")) {
            uri = resolveModuleRelativePath(forwardConfig, servletContext, request);
        }


        if (response.isCommitted() && !forwardConfig.getRedirect()) {
            handleAsInclude(uri, servletContext, request, response);
        } else if (forwardConfig.getRedirect()) {
            handleAsRedirect(uri, request, response);
        } else {
            handleAsForward(uri, servletContext, request, response);
        }
    }

    private String resolveModuleRelativePath(ForwardConfig forwardConfig, ServletContext servletContext, HttpServletRequest request) {
        String prefix = forwardConfig.getModule();
        ModuleConfig moduleConfig = ModuleUtils.getInstance().getModuleConfig(prefix, request, servletContext);
        return RequestUtils.forwardURL(request,forwardConfig, moduleConfig);
    }

    private void handleAsForward(String uri, ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = servletContext.getRequestDispatcher(uri);

        if (LOG.isDebugEnabled()) {
            LOG.debug("Forwarding to " + uri);
        }

        rd.forward(request, response);
    }

    private void handleAsRedirect(String uri, HttpServletRequest request, HttpServletResponse response) throws IOException {
        if (uri.startsWith("/")) {
            uri = request.getContextPath() + uri;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Redirecting to " + uri);
        }

        response.sendRedirect(response.encodeRedirectURL(uri));
    }

    private void handleAsInclude(String uri, ServletContext servletContext, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        RequestDispatcher rd = servletContext.getRequestDispatcher(uri);

        if (rd == null) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR,
                "Error getting RequestDispatcher for " + uri);
            return;
        }

        if (LOG.isDebugEnabled()) {
            LOG.debug("Including " + uri);
        }

        rd.include(request, response);
    }
}
