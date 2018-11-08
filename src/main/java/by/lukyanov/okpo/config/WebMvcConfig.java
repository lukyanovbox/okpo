package by.lukyanov.okpo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import by.lukyanov.okpo.service.RequestCounterInterceptor;
import io.prometheus.client.CollectorRegistry;
import io.prometheus.client.Counter;


@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

   @Autowired
   RequestCounterInterceptor interceptor;

   @Override
   public void addInterceptors(InterceptorRegistry registry) {
      registry.addInterceptor(interceptor);
   }

   @Bean
   public Counter httpTotalRequests(CollectorRegistry registry) {
      return Counter
            .build()
            .name("http_requests_total")
            .labelNames("method", "handler", "status")
            .help("Http Request Total")
            .register(registry);
   }
}