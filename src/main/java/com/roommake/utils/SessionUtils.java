package com.roommake.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.condition.RequestConditionHolder;

import java.util.Objects;

public class SessionUtils {

    public static void addAttribute(String name, Object value) {
        Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).setAttribute(name, value, RequestAttributes.SCOPE_SESSION);
    }

    public static String getStringAttributeValue(String name) {
        return String.valueOf(getAttribute(name));
    }

    public static Object getAttribute(String name) {
        return Objects.requireNonNull(RequestContextHolder.getRequestAttributes()).getAttribute(name, RequestAttributes.SCOPE_SESSION);
    }
}
