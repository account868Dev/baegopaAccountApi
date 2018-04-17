package com.baegopa.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.scheduling.annotation.EnableAsync;

/*
Reactive 가 붙은 거는 전부 스프링5 니까 관심있으면 보구
안봐도 괜찮아 ㅋㅋ
Router도 그렇고
AccountController -> AccountService -> UserRepository -> Model/entity/user 이렇게 보면 되구
이해하기 힘든 부분 많을 수 도 있어
걱정 하지말고 하나씩
 */
@EnableAsync
@PropertySources(value = @PropertySource("classpath:application.yml"))
@SpringBootApplication
public class AccountApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountApplication.class, args);
	}
}
