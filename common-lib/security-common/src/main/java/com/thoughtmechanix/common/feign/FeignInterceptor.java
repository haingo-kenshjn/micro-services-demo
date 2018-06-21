package com.thoughtmechanix.common.feign;

import com.thoughtmechanix.common.util.UserContext;
import com.thoughtmechanix.common.util.UserContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeignInterceptor implements RequestInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(FeignInterceptor.class);

    @Override
    public void apply(RequestTemplate template) {
        logger.debug("Auth Token: " + UserContextHolder.getContext().getAuthToken());

        template.header(UserContext.CORRELATION_ID, UserContextHolder.getContext().getCorrelationId());
        template.header(UserContext.AUTH_TOKEN, UserContextHolder.getContext().getAuthToken());
    }
}
