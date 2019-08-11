package ru.aplana.XAKATOH.controller;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController {
    public static String getRemoteAddr() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRemoteAddr();
    }

    protected static String getRequestURI() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes())
                .getRequest().getRequestURI();
    }

}
