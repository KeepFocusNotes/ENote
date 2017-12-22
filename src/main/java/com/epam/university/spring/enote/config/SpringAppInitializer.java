package com.epam.university.spring.enote.config;

import org.springframework.lang.Nullable;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

public class SpringAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

  @Nullable
  @Override
  protected Class<?>[] getRootConfigClasses() {
    return new Class<?>[]{AppConfig.class};
  }

  @Nullable
  @Override
  protected Class<?>[] getServletConfigClasses() {
    return new Class<?>[]{AppConfig.class};
  }

  @Nullable
  @Override
  protected String[] getServletMappings() {
    return new String[]{"/"};
  }
}
