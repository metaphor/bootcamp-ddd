package com.newchiwan.council.bootcamp.ddd.framework.persistence;

import com.querydsl.core.types.EntityPath;
import com.querydsl.jpa.impl.JPAQuery;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.Optional;

public abstract class QueryDslSupport {

    protected EntityManager entityManager;

    protected <T> JPAQuery<T> selectFrom(EntityPath<T> entity) {
        return new JPAQuery<T>(entityManager).from(entity);
    }

    protected <T> Optional<T> loadById(Class<T> entityClass, Object primaryKey) {
        return Optional.ofNullable(entityManager.find(entityClass, primaryKey));
    }

    protected <T> void saveOrUpdate(T entity) {
        if (entityManager.contains(entity)) {
            entityManager.merge(entity);
            return;
        }
        entityManager.persist(entity);
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
}
