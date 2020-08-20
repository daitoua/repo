package com.daitou.o2o.cache;

import com.daitou.o2o.cache.JedisPoolWriper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.util.SafeEncoder;
import java.util.Set;

@Component
public class JedisUtil {

    /** 操作Key的方法 */
    public Keys KEYS;
    /** 对存储结构为String类型的操作 */
    public Strings STRINGS;
    /** 连接池对象*/
    private JedisPool jedisPool;
    //set方法注入jedisPool
    public void setJedisPool(JedisPoolWriper jedisPoolWriper) {
        this.jedisPool = jedisPoolWriper.getJedisPool();
    }

    public JedisPool getJedisPool() {
        return jedisPool;
    }
    public Jedis getJedis(){
        return jedisPool.getResource();
    }

    //**********************************keys*******************************************//
    public class Keys{

        /*public Keys(JedisUtil jedisUtil) {

        }*/
            /**
             * 清空所有键
             * @return*/

            public String flushAll () {
                Jedis jedis = getJedis();
                String state = jedis.flushAll();
                jedis.close();
                return state;

            }

            /**
             * 根据key删除对应的记录，key可以是多个
             * @param keys
             * @return*/

            public Long delkeys (String...keys){
                Jedis jedis = getJedis();
                Long del = jedis.del(keys);
                jedis.close();
                return del;
            }

            /**
             * 判断key存不存在
             * @param key
             * @return*/

            public boolean exists (String key){

                Jedis jedis = getJedis();
                Boolean exists = jedis.exists(key);
                jedis.close();
                return exists;
            }

            /**
             * 查找所有匹配给定的模式的键
             * @param pattern
             * @return
             */
            public Set<String> keys (String pattern){

                Jedis jedis = getJedis();
                Set<String> set = jedis.keys(pattern);
                jedis.close();
                return set;

            }
        }






    //*********************************Strings*******************************************//
    public class Strings{


        /*public Strings(JedisUtil jedisUtil) {

        }*/

        /**
         * 通过键获得值
         * @param key
         * @return*/

        public String get(String key){
            Jedis jedis = getJedis();
            String value = jedis.get(key);
            jedis.close();
            return value;

        }

        /**
         * 将String的key和value转换成byte数组，调用set方法存入记录
         * @param key
         * @param value
         * @return*/

        public String set(String key,String value){

            return set(SafeEncoder.encode(key),SafeEncoder.encode(value));

        }

        /**
         * 添加记录，如果记录已存在则覆盖
         * @param key
         * @param value
         * @return*/

        public String set(byte[] key, byte[] value){

            Jedis jedis = getJedis();
            String status = jedis.set(key, value);
            jedis.close();

            return status;
        }



    }

}
