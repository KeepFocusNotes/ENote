package com.epam.university.spring.enote.cache;

import com.epam.university.spring.enote.config.AppConfig;
import com.epam.university.spring.enote.model.User;
import com.epam.university.spring.enote.services.UserService;
import com.epam.university.spring.enote.util.JpaUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@ContextConfiguration(classes = AppConfig.class)
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@RunWith(SpringRunner.class)
public class HibernateSecondLevelCacheTest {
    private static final Logger log = LoggerFactory.getLogger(HibernateSecondLevelCacheTest.class);

    @Autowired
    private UserService userService;

    @Autowired
    private CacheManager cacheManager;

    @Autowired
    protected JpaUtil jpaUtil;

    @Before
    public void setUp() throws Exception {
        cacheManager.getCache("users").clear();
        jpaUtil.clear2ndLevelHibernateCache();
    }

    @Test
    public void isPopulatedL2Cache() {
        long entitiesCountBeforeCall = jpaUtil.getSecondLevelCacheStatistics("users")
                .getEntries().size();
        userService.getAll();
        long entitiesCountAfterCall = jpaUtil.getSecondLevelCacheStatistics("users")
                .getEntries().size();
        assertTrue(entitiesCountBeforeCall == 0 && entitiesCountAfterCall == 500);
    }

    @Test
    public void isReadingFromL2Cache() {
        Session session0 = jpaUtil.getSessionFactory().openSession();
        Transaction transaction0 = session0.beginTransaction();
        //print to panel to init counter of getting from db probably
        log.info("Load first: " + session0.load(User.class, 1));
        transaction0.commit();
        session0.close();
        Session session1 = jpaUtil.getSessionFactory().openSession();
        Transaction transaction1 = session1.beginTransaction();
        log.info("Load second: " + session1.load(User.class, 1));
        transaction1.commit();
        session1.close();
        Session session2 = jpaUtil.getSessionFactory().openSession();
        Transaction transaction2 = session2.beginTransaction();
        log.info("Load third: " + session2.load(User.class, 1));
        transaction2.commit();
        session2.close();
        Session session3 = jpaUtil.getSessionFactory().openSession();
        Transaction transaction3 = session3.beginTransaction();
        log.info("Load forth: " + session3.load(User.class, 1));
        transaction3.commit();
        session3.close();
        assertEquals(3, jpaUtil.getSecondLevelCacheStatistics("users").getHitCount());
    }
}