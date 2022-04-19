package cn.dangkei.cardservice;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/demo")
public class DemoController {

	@RequestMapping("/hello")
	public Mono<String> demo(){
		return Mono.just("this is Mono");
	}

}
