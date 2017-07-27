package org.world.jedis;

import java.util.List;
import java.util.Set;

import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 测试使用jedis连接redis
 * 
 * @author 付石强
 *
 */
public class TestJedis {

	@Test
	public void testJedisSingle() {
		// 设置ip地址和端口
		Jedis jedis = new Jedis("192.168.1.107", 6379);
		// 设置数据
		// String set = jedis.set("username", "l love Java");
		String username = jedis.get("username");
		System.out.println(username);
		// 设置资源
		jedis.close();
	}
	
	@Test
	public void testList() {
		Jedis jedis = new Jedis("192.168.1.107", 6379);
		List<String> list = jedis.lrange("mylist", 0, -1);
		for (String string : list) {
			System.out.print(string);
		}
		jedis.close();
	}
	
	@Test
	public void testJedisPool() {
		// 获得连接池配置对象
		JedisPoolConfig config = new JedisPoolConfig();
		// 设置最大连接数
		config.setMaxTotal(30);
		// 设置最大空闲连接数
		config.setMaxIdle(10);
		// 获得连接池
		JedisPool jedisPool = new JedisPool(config, "192.168.1.107", 6379);
		//获得核心对象
		Jedis jedis = null;
		try {
			jedis = jedisPool.getResource();
			// 设置数据
			jedis.set("name", "Hello");
			// 获取数据
			String name = jedis.get("name");
			System.out.println(name);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (jedis != null) {
				jedis.close();
			}
			if (jedisPool != null) {
				jedisPool.close();
			}
		}
	}
}
