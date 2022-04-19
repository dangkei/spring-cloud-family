package cn.dangkei.ignored;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
@RequestMapping("/test")
public class IgnoredServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(IgnoredServiceApplication.class, args);
	}

	@GetMapping()
	String test() {
		return "ignored service called!";
	}
	
	@GetMapping("/allowed")
	String allowed() {
		return "Allowed endpoint called !!";
	}
}
