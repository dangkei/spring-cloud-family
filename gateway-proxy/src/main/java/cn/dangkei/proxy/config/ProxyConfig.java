package cn.dangkei.proxy.config;

import javax.ws.rs.HttpMethod;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProxyConfig {

	@Bean
	RouteLocator customRouteKocator(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("users_service_route",
						route->route.path("/user-service")
						.and()
						.method(HttpMethod.POST)
						.filters(filter->filter.stripPrefix(1))
						.uri("lb://user-service")).build();
		
	}

}
