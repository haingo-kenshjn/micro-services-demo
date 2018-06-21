package com.thoughtmechanix.common.util;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;


@Component
public class UserContext {
    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN = "Authorization";
    public static final String USER_ID = "tmx-user-id";
    public static final String ORG_ID = "tmx-org-id";
    public static final String TRACE_ID = "tmx-trace-id";


    public static final ThreadLocal<Map<String, String>> data = new ThreadLocal();

    public UserContext() {
        data.set(new HashMap<String, String>());
    }

    public static Map<String, String> getData() {
        return data.get();
    }
    public static void setData(Map<String, String> datamap) {
        data.set(datamap);
    }


    public static String getCorrelationId() {
        return data.get().get(CORRELATION_ID);
    }

    public static void setCorrelationId(String cid) {
        data.get().put(CORRELATION_ID, cid);
    }

    public static String getAuthToken() {
        return data.get().get(AUTH_TOKEN);
    }

    public static void setAuthToken(String aToken) {
        data.get().put(AUTH_TOKEN, aToken);
    }

    public static String getUserId() {
        return data.get().get(USER_ID);
    }

    public static void setUserId(String aUser) {
        data.get().put(USER_ID, aUser);
    }

    public static String getOrgId() {
        return data.get().get(ORG_ID);
    }

    public static void setOrgId(String aOrg) {
        data.get().put(ORG_ID, aOrg);
    }

    public static String getTraceId() {
        return data.get().get(TRACE_ID);
    }

    public static void setTraceId(String aTrace) {
        data.get().put(TRACE_ID, aTrace);
    }
}
