package com.yzzzzun.openmat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

@EnableJpaAuditing
@SpringBootApplication
@EnableRedisHttpSession
@EnableAspectJAutoProxy
public class OpenMatApplication {

	public static void main(String[] args) {
		SpringApplication.run(OpenMatApplication.class, args);
	}

}


