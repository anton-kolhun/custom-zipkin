package com.experiment.zipkin.config;

import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.boot.actuate.autoconfigure.metrics.MetricsProperties;
import org.springframework.boot.actuate.metrics.web.servlet.WebMvcMetricsFilter;
import org.springframework.boot.actuate.metrics.web.servlet.WebMvcTagsProvider;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.WebApplicationContext;

import javax.servlet.DispatcherType;

@Configuration
public class AppConfig {

    //@Bean
    public FilterRegistrationBean filterRegistrationBean(WebMvcMetricsFilter webMvcMetricsFilter) {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(webMvcMetricsFilter);
        filterRegistrationBean.setEnabled(false);
        return filterRegistrationBean;
    }

    //@Bean
    public FilterRegistrationBean<WebMvcMetricsFilter> webMvcMetricsFilter(MeterRegistry registry, MetricsProperties properties, WebMvcTagsProvider tagsProvider, WebApplicationContext context) {
        MetricsProperties.Web.Server serverProperties = properties.getWeb().getServer();
        WebMvcMetricsFilter filter = new WebMvcMetricsFilter(context, registry, tagsProvider, serverProperties.getRequestsMetricName(), serverProperties.isAutoTimeRequests());
        FilterRegistrationBean<WebMvcMetricsFilter> registration = new FilterRegistrationBean(filter, new ServletRegistrationBean[0]);
        registration.setOrder(-2147483647);
        registration.setDispatcherTypes(DispatcherType.REQUEST, new DispatcherType[]{DispatcherType.ASYNC});
        registration.setEnabled(false);
        return registration;
    }
}
