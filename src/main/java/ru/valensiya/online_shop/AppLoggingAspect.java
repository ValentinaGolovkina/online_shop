package ru.valensiya.online_shop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Aspect
@Component
public class AppLoggingAspect {

    private Map<String, Long> statisticMap;

    @PostConstruct
    private void init() {
        this.statisticMap = new HashMap<>();
    }

    @Around("execution(public * ru.valensiya.online_shop.services.*Service.*(..))")
    public Object methodProfiling(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("start profiling");
        long begin = System.currentTimeMillis();
        Object out = proceedingJoinPoint.proceed();
        long duration = System.currentTimeMillis() - begin;
        String name = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
        statisticMap.put(name, statisticMap.getOrDefault(name, 0L) + duration);
        return out;
    }

    public Map<String, Long> getStatistics() {
        return statisticMap;
    }
}
