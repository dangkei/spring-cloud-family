package cn.dangkei.cardservice.config;

import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.DefaultResponse;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;

import reactor.core.publisher.Mono;

public class TestRoundRobinLoadBalancer implements ReactorServiceInstanceLoadBalancer{
	private static final Log LOG = LogFactory.getLog(TestRoundRobinLoadBalancer.class);
	
	private final AtomicInteger position;
	
	private final ObjectProvider<ServiceInstanceListSupplier> serviceInstanceSupplier;
	
	private final String serviceId;

	public TestRoundRobinLoadBalancer(String serviceId,
			ObjectProvider<ServiceInstanceListSupplier> serviceInstanceSupplier) {
		this(serviceId,serviceInstanceSupplier,new Random().nextInt(1000));  //返回随机
		// TODO Auto-generated constructor stub
	}
	public TestRoundRobinLoadBalancer(String serviceId,
			ObjectProvider<ServiceInstanceListSupplier> serviceInstanceSupplier,
			int seedPostion) {
		// TODO Auto-generated constructor stub
		this.serviceInstanceSupplier = serviceInstanceSupplier;
		this.serviceId = serviceId;
		this.position = new AtomicInteger(seedPostion);
	}

	@Override
	public Mono<Response<ServiceInstance>> choose(Request request) {
		// TODO Auto-generated method stub
		LOG.info("使用 " +TestRoundRobinLoadBalancer.class
				.getSimpleName()+" 检索到一个服务实例. ");
		ServiceInstanceListSupplier supplier =  this.serviceInstanceSupplier
				.getIfAvailable();
		return supplier.get().next().map(instances->{
			if(instances.isEmpty()) {
			LOG.warn("没有服务器为" + this.serviceId+"提供服务");	
			}
			int pos = Math.abs(this.position.incrementAndGet());
			
			ServiceInstance instance = instances.get(pos % instances.size());
			
			return  new DefaultResponse(instance);
		});
	}

}
