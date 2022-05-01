package com.assignment.ecommerce.security;

public class JwtConstants {
	private JwtConstants() {

	}

	public static final String SECRET = "a1b2c3d4";

	public static final long EXPIRATION_TIME = 864_000_000; // 10 days

	public static final String TOKEN_PREFIX = "Bearer ";

	public static final String HEADER_STRING = "Authorization";
}
