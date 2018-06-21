package com.thoughtmechanix.common.hystrix;

import com.netflix.hystrix.HystrixInvokable;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import com.netflix.hystrix.strategy.concurrency.HystrixRequestVariableDefault;
import com.netflix.hystrix.strategy.executionhook.HystrixCommandExecutionHook;
import com.thoughtmechanix.common.util.UserContextHolder;

import java.util.Map;

public class UserContextCommandExecutionHook extends HystrixCommandExecutionHook {
    private HystrixRequestVariableDefault<Map<String, String>> userContextVariable = new HystrixRequestVariableDefault();

    public <T> void onStart(HystrixInvokable<T> commandInstance) {
        extractUserContext();
    }

    public <T> void onThreadStart(HystrixInvokable<T> commandInstance) {
        setupUserContext();
    }

    public <T> void onFallbackStart(HystrixInvokable<T> commandInstance) {
        setupUserContext();
    }

    private void setupUserContext() {
        if (HystrixRequestContext.getContextForCurrentThread() == null) {
            extractUserContext();
        }
        if (userContextVariable.get() != null) {
            UserContextHolder.getContext().setData(userContextVariable.get());
        }
    }

    private void extractUserContext() {
        HystrixRequestContext.initializeContext();

        userContextVariable.set(UserContextHolder.getContext().getData());
    }

    public <T> Exception onFallbackError(HystrixInvokable<T> commandInstance, Exception e) {
        cleanup();
        return e;
    }

    public <T> void onFallbackSuccess(HystrixInvokable<T> commandInstance) {
        cleanup();
    }

    public <T> Exception onExecutionError(HystrixInvokable<T> commandInstance, Exception e) {
        cleanup();
        return e;
    }

    public <T> void onExecutionSuccess(HystrixInvokable<T> commandInstance) {
        cleanup();
    }

    private void cleanup() {
        HystrixRequestContext.getContextForCurrentThread().shutdown();
    }
}
