package io.renren;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author lanxinghua
 * @date 2018/08/04 15:07
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring-mvc.xml","classpath:spring-jdbc.xml",
"classpath:spring-redis.xml","classpath:spring-scheduler.xml"})
public class BaseTest {
    public static String WEBHOOK = "https://oapi.dingtalk.com/robot/send?access_token=fea467755cd4fcf7ba780fbe8fe934a28ec546f46347487e5ad73a1f29a16787";
}