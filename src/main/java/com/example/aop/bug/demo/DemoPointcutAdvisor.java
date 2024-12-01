package com.example.aop.bug.demo;

import org.aopalliance.aop.Advice;
import org.aopalliance.intercept.MethodInterceptor;
import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;
import org.springframework.aop.support.AbstractPointcutAdvisor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Component
public class DemoPointcutAdvisor extends AbstractPointcutAdvisor {
    @Override
    public Pointcut getPointcut() {
        return new Pointcut() {
            @Override
            public ClassFilter getClassFilter() {
                return clazz -> clazz.getSimpleName().equals("DemoBean");
            }

            @Override
            public MethodMatcher getMethodMatcher() {
                return new MethodMatcher() {
                    @Override
                    public boolean matches(Method method, Class<?> targetClass) {
                        if (method.getName().equals("sayHello")) {
                            System.out.println("do matches");
                        }
                        return true;
                    }

                    @Override
                    public boolean isRuntime() {
                        return false;
                    }

                    @Override
                    public boolean matches(Method method, Class<?> targetClass, Object... args) {
                        return true;
                    }
                };
            }
        };
    }

    @Override
    public Advice getAdvice() {
        return (MethodInterceptor) invocation -> {
            System.out.println("before advice");
            Object proceed = invocation.proceed();
            System.out.println("after advice");
            return proceed;
        };
    }
}
