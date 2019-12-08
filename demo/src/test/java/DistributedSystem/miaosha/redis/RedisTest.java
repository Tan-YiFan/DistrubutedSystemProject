package DistributedSystem.miaosha.redis;


import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class RedisTest {
    private static final Long RELEASE_SUCCESS=1L;
    private static final String LOCK_SUCCESS="OK";
    private static final String SET_IF_NOT_EXIST="NX";
    private static final String SET_WITH_EXPIRE_TIME="PX";

    public static void main(String[] args){

//        Config config=new Config();
//        config.useClusterServers()
//                .setScanInterval(2000) // 集群状态扫描间隔时间，单位是毫秒
//                //可以用"rediss://"来启用SSL连接
//                .addNodeAddress("redis://172.101.8.2:8001")
//                .addNodeAddress("redis://172.101.8.3:8002")
//                .addNodeAddress("redis://172.101.8.4:8003")
//                .addNodeAddress("redis://172.101.8.5:8004")
//                .addNodeAddress("redis://172.101.8.6:8005")
//                .addNodeAddress("redis://172.101.8.7:8006");
//
//        RedissonClient redisson = Redisson.create(config);
//
        Set<HostAndPort> nodes = new HashSet<>();
        nodes.add(new HostAndPort("172.101.8.7", 8006));
        nodes.add(new HostAndPort("172.101.8.6", 8005));
        nodes.add(new HostAndPort("172.101.8.5", 8004));
        nodes.add(new HostAndPort("172.101.8.4", 8003));
        nodes.add(new HostAndPort("172.101.8.3", 8002));
        nodes.add(new HostAndPort("172.101.8.2", 8001));
        JedisPoolConfig config = new JedisPoolConfig();
        JedisCluster jedis = new JedisCluster(nodes, 2000, 2000, 100, "123456", config);
        jedis.set("test","1");
        jedis.set("test2","hhh");
        System.out.println(jedis.hkeys("*"));
//        boolean result=tryGetDistributedLock(jedis,"TestLock","123",500);
//        System.out.println(result);
//        result=releaseDistributedLock(jedis,"TestLock","123");
//        System.out.println(result);
    }

}
