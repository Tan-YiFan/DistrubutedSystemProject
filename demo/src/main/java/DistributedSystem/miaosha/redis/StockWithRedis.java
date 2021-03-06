package DistributedSystem.miaosha.redis;

import DistributedSystem.miaosha.pojo.Stock;
import lombok.Synchronized;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.Transaction;

import javax.xml.stream.FactoryConfigurationError;
import java.util.List;

@Slf4j
public class StockWithRedis {

    /**
     * 库存值
     */
    public final static String STOCK_COUNT = "stock_count_";

    /**
     * 销售值
     */
    public final static String STOCK_SALE = "stock_sale_";

    /**
     * 重置缓存 缓存预热
     */
    public static int initRedisBefore(int id, int count) throws Exception {
        try {
            RedisPool.set(STOCK_COUNT + id, count);
            RedisPool.set(STOCK_SALE + id, 0);
            return 1;
        } catch (Exception e) {
            System.out.println("initRedis 获取 Jedis 实例失败："+ e);
            return 0;
        }
    }

    public static void initServerBefore(int id ,int count){
        RedisPool.addStockEntry(id,count);
    }



}
