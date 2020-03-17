package io.clayon.devops.localredis;

import redis.embedded.RedisServer;

public class LocalRedisServer {

	public static void main(String[] args) throws Exception {
		
		int port = 16379;
		
		final RedisServer redisServer = RedisServer.builder()
                .port(port)
                //.redisExecProvider(customRedisExec) //com.github.kstyrc (not com.orange.redis-embedded)
                .setting("maxmemory 128M") //maxheap 128M
                .build();
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				redisServer.stop();
			}
		});
		
		try {
			redisServer.start();
			System.out.println("Local Redis startup success port : " + port);
		} catch (Exception e) {
			System.out.println("Local Redis startup error");
			
			throw e;
		} finally {
			
		}
	}

	
}
