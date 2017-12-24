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
    public void isReadingFromL2Cache() {
        log.info("Load first: " + userService.getById(1));
        log.info("Load second: " + userService.getById(1));
        log.info("Load third: " + userService.getById(1));
        log.info("Load forth: " + userService.getById(1));
        assertEquals(3, jpaUtil.getSecondLevelCacheStatistics("users").getHitCount());
    }
}