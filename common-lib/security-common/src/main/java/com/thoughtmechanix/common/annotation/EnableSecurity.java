package com.thoughtmechanix.common.annotation;

import com.thoughtmechanix.common.feign.FeignConfiguration;
import com.thoughtmechanix.common.security.ServiceSecurityConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@EnableResourceServer
@Import(value = {ServiceSecurityConfiguration.class, FeignConfiguration.class})
public @interface EnableSecurity {
}
