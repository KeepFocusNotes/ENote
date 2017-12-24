package com.epam.university.spring.enote.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.stat.SecondLevelCacheStatistics;
import org.hibernate.stat.Statistics;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class JpaUtil {

    @PersistenceContext
    private EntityManager em;

    public void clear2ndLevelHibernateCache() {
        getSessionFactory().getCache().evictAllRegions();
    }

    public Session getSession() {
        return (Session) em.getDelegate();
    }

    public SessionFactory getSessionFactory() {
        return getSession().getSessionFactory();
    }

    public Statistics getStatistics() {
        return getSessionFactory().getStatistics();
    }

    public SecondLevelCacheStatistics getSecondLevelCacheStatistics(String region) {
        return getStatistics().getSecondLevelCacheStatistics(region);
    }
}
