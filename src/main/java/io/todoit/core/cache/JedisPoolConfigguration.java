//package io.todoit.core.cache;
//
//import io.todoit.common.expection.BizException;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//import redis.clients.jedis.Jedis;
//import redis.clients.jedis.JedisPool;
//import redis.clients.jedis.JedisPoolConfig;
//
//import javax.annotation.PostConstruct;
//
///**
// * @className:JedisPoolConfigguration
// * @author:zhangd
// * @date:2019/2/4
// * @description:
// * 初始化redis,采用连接池的方法来管理连接
// */
//@Slf4j
//@Component
//public class JedisPoolConfigguration {
//
//    @Value("${redis.jedis.host}")
//    private String host;
//    @Value("${redis.jedis.port}")
//    private Integer port;
//    @Value("${redis.jedis.max-idle}")
//    private Integer maxIdle;
//    @Value("${redis.jedis.max-total}")
//    private Integer maxTotal;
//    @Value("${redis.jedis.max-wait-millis}")
//    private Long maxWaitMillis;
//
//    private static JedisPool jedisPool;
//
//    /**
//     * @PostConstruct 被用来修饰一个非静态的void（）方法。
//     */
//    @PostConstruct
//    private void init(){
//        try {
//            JedisPoolConfig config = new JedisPoolConfig();
//            config.setMaxWaitMillis(maxWaitMillis);
//            config.setMaxIdle(maxIdle);
//            config.setMaxTotal(maxTotal);
//            jedisPool = new JedisPool(config,host, port);
//        } catch (Exception e) {
//            log.error("Fail initialize redis pool",e);
//            throw new BizException("初始化jedis连接池失败");
//        }
//    }
//
//    /**
//     * 获取连接池，采用单例模式
//     * @return
//     */
//    public JedisPool getRedisPool(){
//        if(jedisPool == null){
//            synchronized (JedisPoolConfigguration.class){
//                if(jedisPool == null){
//                    init();
//                    return jedisPool;
//                }
//            }
//        }
//        return jedisPool;
//    }
//
//    /**
//     * 获得redis连接
//     * @return Jedis
//     */
//    public static Jedis getJedis(){
//        try {
//            return jedisPool.getResource();
//        } catch (Exception e) {
//            log.error("Fail get Jedis",e);
//            throw new BizException("获取jedis连接失败");
//        }
//    }
//
//
//    /**
//     * 关闭连接池
//     */
//    public static void closeResourse(Jedis jedis){
//        try {
//            jedis.close();
//        } catch (Exception e) {
//            log.error("Fail close Jedis",e);
//            throw new BizException("初始化jedis连接池失败");
//        }
//    }
//}
