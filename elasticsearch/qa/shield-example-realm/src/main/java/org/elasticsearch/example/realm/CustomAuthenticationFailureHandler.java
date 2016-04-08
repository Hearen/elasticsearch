/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License;
 * you may not use this file except in compliance with the Elastic License.
 */
package org.elasticsearch.example.realm;

import org.elasticsearch.ElasticsearchSecurityException;
import org.elasticsearch.common.util.concurrent.ThreadContext;
import org.elasticsearch.rest.RestRequest;
import org.elasticsearch.shield.authc.AuthenticationToken;
import org.elasticsearch.shield.authc.DefaultAuthenticationFailureHandler;
import org.elasticsearch.transport.TransportMessage;

public class CustomAuthenticationFailureHandler extends DefaultAuthenticationFailureHandler {

    @Override
    public ElasticsearchSecurityException failedAuthentication(RestRequest request, AuthenticationToken token,
                                                               ThreadContext context) {
        ElasticsearchSecurityException e = super.failedAuthentication(request, token, context);
        // set a custom header
        e.addHeader("WWW-Authenticate", "custom-challenge");
        return e;
    }

    @Override
    public ElasticsearchSecurityException failedAuthentication(TransportMessage message, AuthenticationToken token, String action,
                                                               ThreadContext context) {
        ElasticsearchSecurityException e = super.failedAuthentication(message, token, action, context);
        // set a custom header
        e.addHeader("WWW-Authenticate", "custom-challenge");
        return e;
    }

    @Override
    public ElasticsearchSecurityException missingToken(RestRequest request, ThreadContext context) {
        ElasticsearchSecurityException e = super.missingToken(request, context);
        // set a custom header
        e.addHeader("WWW-Authenticate", "custom-challenge");
        return e;
    }

    @Override
    public ElasticsearchSecurityException missingToken(TransportMessage message, String action, ThreadContext context) {
        ElasticsearchSecurityException e = super.missingToken(message, action, context);
        // set a custom header
        e.addHeader("WWW-Authenticate", "custom-challenge");
        return e;
    }
}
