package by.lukyanov.okpo.service;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import io.prometheus.client.Counter;


@Service
public class RequestCounterInterceptor extends HandlerInterceptorAdapter {

   @Autowired
   private Counter httpTotalRequests;

   @Override
   public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception e)
         throws Exception {
      // Update counters
      String handlerLabel = handler.toString();
      // get short form of handler method name
      if (handler instanceof HandlerMethod) {
         Method method = ((HandlerMethod) handler).getMethod();
         handlerLabel = method.getDeclaringClass().getSimpleName() + "." + method.getName();
      }
      // Note (2)
      httpTotalRequests.labels(request.getMethod(), handlerLabel, Integer.toString(response.getStatus())).inc();
   }
}