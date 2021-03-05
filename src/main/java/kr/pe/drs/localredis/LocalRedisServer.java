package kr.pe.drs.localredis;

import redis.embedded.RedisServer;

public class LocalRedisServer {

	public static void main(String[] args) throws Exception {
		
		String strPort = "16379";
		String strMemory = "128M";
		
		if (args.length > 0) {
			
			strPort = args[0];
		}
		
		if (args.length > 1) {
			strMemory = args[1];
		}
		
		int port = Integer.parseInt(strPort);
		
		final RedisServer redisServer = RedisServer.builder()
                .port(port)
                .setting("maxmemory " + strMemory) //maxheap 128M
                .build();
		
		Runtime.getRuntime().addShutdownHook(new Thread() {
			public void run() {
				redisServer.stop();
				System.out.println("Local Redis was stopped");
			}
		});
		
		try {
			redisServer.start();
			
			System.out.println("Local Redis startup success port : " + port + " / maxmemory : " + strMemory);
			
			while (redisServer.isActive()) {
				Thread.sleep(5000);
			}
			
		} catch (Exception e) {
			
			System.out.println("Local Redis startup error");
			
			throw e;
		} finally {
			redisServer.stop();
			System.out.println("Local Redis was stopped");
		}
	}

	
}