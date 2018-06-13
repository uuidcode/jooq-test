package com.github.uuidcode.jooq.test;

import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.github.uuidcode.jooq.test.configuration.WebConfiguration;
import com.github.uuidcode.jooq.test.util.CoreUtil;

@ContextConfiguration(classes = {WebConfiguration.class})
@RunWith(SpringJUnit4ClassRunner.class)
public class CoreTest {
    protected static Logger logger = LoggerFactory.getLogger(CoreTest.class);

    public void printJson(Object object) {
        if (logger.isDebugEnabled()) {
            logger.debug(">>> printJson object: {}", CoreUtil.toJson(object));
        }
    }
}
