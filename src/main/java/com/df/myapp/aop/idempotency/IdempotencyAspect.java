package com.df.myapp.aop.idempotency;

import com.df.myapp.service.RedisIdempotencyService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Aspect
@Component
public class IdempotencyAspect {

    @Autowired
    private RedisIdempotencyService redisIdempotencyService;

    @Around("@annotation(idempotent)")
    public Object enforceIdempotency(ProceedingJoinPoint joinPoint, Idempotent idempotent) throws Throwable {
        Object[] args = joinPoint.getArgs();
        if (args.length == 0) {
            throw new IllegalArgumentException("Method annotated with @Idempotent must have at least one argument");
        }
        CodeSignature codeSignature = (CodeSignature) joinPoint.getSignature();
        List<String> parameterNames = List.of(codeSignature.getParameterNames());
        long expiration = idempotent.expirationSeconds();
        List<String> targetParameterNames = List.of(idempotent.parameterNames());
        List<Integer> targetParameterIndex = targetParameterNames.stream()
                .map(parameterNames::indexOf)
                .toList();

        List<Object> idempotentArguments = Arrays.stream(args)
                .filter(arg -> targetParameterIndex.contains(parameterNames.indexOf(arg.getClass().getSimpleName())))
                .collect(Collectors.toList());

        String key = redisIdempotencyService.generateKey(idempotentArguments);

        if (!redisIdempotencyService.isIdempotent(key, expiration)) {
            throw new IllegalStateException("Duplicate request detected");
        }

        // Proceed with the method execution
        return joinPoint.proceed();
    }
}
