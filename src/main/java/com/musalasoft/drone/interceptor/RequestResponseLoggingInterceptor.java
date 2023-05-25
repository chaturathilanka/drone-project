package com.musalasoft.drone.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

public class RequestResponseLoggingInterceptor implements HandlerInterceptor {

    private static final Logger logger = LoggerFactory.getLogger(RequestResponseLoggingInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestId = generateRequestId();
        request.setAttribute("RequestId", requestId);
        logger.info("Request received - ID: {}, Method: {}, Path: {}", request.getAttribute("RequestId"), request.getMethod(), request.getRequestURI());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String requestId = (String) request.getAttribute("RequestId");
        response.setHeader("RequestId", requestId);
        logger.info("Response sent - ID: {}, Status: {}", request.getAttribute("RequestId"), response.getStatus());
    }

    private String generateRequestId() {
        return UUID.randomUUID().toString();
    }
}

