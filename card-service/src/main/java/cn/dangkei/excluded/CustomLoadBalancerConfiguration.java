package cn.dangkei.excluded;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.CompletionContext;
import org.springframework.cloud.client.loadbalancer.LoadBalancerLifecycle;
import org.springframework.cloud.client.loadbalancer.Request;
import org.springframework.cloud.client.loadbalancer.RequestDataContext;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.client.ClientResponse;

import cn.dangkei.cardservice.config.TestRoundRobinLoadBalancer;

public class CustomLoadBalancerConfiguration {

	public CustomLoadBalancerConfiguration() {
		// TODO Auto-generated constructor stub
	}
	
	@Bean
	ReactorLoadBalancer<ServiceInstance> reactorLoadBalancer(Environment environment,
			LoadBalancerClientFactory loadBalancerClientFactory){
		String name = environment.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
		return new TestRoundRobinLoadBalancer(name,loadBalancerClientFactory
				.getLazyProvider(name, ServiceInstanceListSupplier.class));
	}
	
	@Bean
	LoadBalancerLifecycle loadBalancerLifecycle(){
		return new TestLoadBalancerLifecycle();
	}

}

class TestLoadBalancerLifecycle implements LoadBalancerLifecycle<RequestDataContext,ClientResponse,ServiceInstance> {

	private static final Log LOG = LogFactory.getLog(TestLoadBalancerLifecycle.class);
	
	@Override
	public boolean supports(Class requestContextClass, Class responseClass, Class serverTypeClass) {
		return RequestDataContext.class.isAssignableFrom(requestContextClass)
				&& ClientResponse.class.isAssignableFrom(responseClass)
				&& ServiceInstance.class.isAssignableFrom(serverTypeClass);
	}
	
	@Override
	public void onStart(Request<RequestDataContext> request) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStartRequest(Request<RequestDataContext> request, Response<ServiceInstance> lbResponse) {
		// TODO Auto-generated method stub
		LOG.error("REQUEST: "+request);
		
	}

	@Override
	public void onComplete(CompletionContext<ClientResponse, ServiceInstance, RequestDataContext> completionContext) {
		// TODO Auto-generated method stub
		LOG.error("COMPLETION: "+ completionContext);
	}}
