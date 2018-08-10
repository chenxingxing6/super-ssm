package io.renren;

import io.renren.common.utils.RedisUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
public class RedisTest extends BaseTest {
    @Autowired
    private RedisUtils redisUtils;

    @Test
    public void test(){
        //测试redis，需要设置renren.redis.open=true
        redisUtils.set("domain", "renren.io");
        String domain = redisUtils.get("domain");
        System.out.println(domain);
    }
}
