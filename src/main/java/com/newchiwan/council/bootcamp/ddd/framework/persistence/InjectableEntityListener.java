package com.newchiwan.council.bootcamp.ddd.framework.persistence;

import jakarta.persistence.PostLoad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.ApplicationContext;

@Configurable
public class InjectableEntityListener {

    private ApplicationContext applicationContext;

    @PostLoad
    public void postLoad(Object entity) {
        applicationContext.getAutowireCapableBeanFactory().autowireBean(entity);
    }

    @Autowired
    public void setApplicationContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }
}
