package org.portfolio.hardwarecontrollerapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class HardwareControllerApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(HardwareControllerApiApplication.class, args);
	}

}
