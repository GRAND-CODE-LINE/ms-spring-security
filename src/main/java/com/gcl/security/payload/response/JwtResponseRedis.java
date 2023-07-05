package com.gcl.security.payload.response;

import java.util.List;

import org.springframework.data.redis.core.RedisHash;

import org.springframework.data.redis.core.index.Indexed;


public class JwtResponseRedis {
	private String token;
	private String type = "Bearer";
	private String id;
	private String username;
	private String email;
	private List<String> roles;

	

	
}