package com.prodyna.conference.common.monitoring;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.interceptor.Interceptors;

@Retention(RetentionPolicy.RUNTIME)
@Interceptors(MonitoringInterceptor.class)
@Target(value = { ElementType.TYPE, ElementType.METHOD })
public @interface Monitored {

}
