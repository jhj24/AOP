package com.jhj.aop.aspect;


import android.util.Log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;

import java.util.concurrent.TimeUnit;

@Aspect
public class TimeStatisticsAspect {

    private String TAG = "aaa";

    @Around("execution(@com.jhj.aop.annotation.TimeStatistics void *(..))")
    public void statistics(ProceedingJoinPoint joinPoint) {
        long runNano;
        long startNano = System.nanoTime();
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();
        try {
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        runNano = System.nanoTime() - startNano;
        long runMillis = TimeUnit.NANOSECONDS.toMillis(runNano);
        logger(className, methodName, runMillis);
    }

    private void logger(String className, String methodName, long runMillis) {
        String doubleLine = "==============================================";
        String singleLine = "----------------------------------------";
        logger(doubleLine + doubleLine);
        logger(singleLine + "class" + singleLine);
        logger(className);
        logger(singleLine + "Method" + singleLine);
        logger(methodName);
        logger(singleLine + "RunTime" + singleLine);
        logger(runMillis + "ms");
        logger(doubleLine + doubleLine);
    }

    private void logger(String message) {
        Log.d(TAG, "||" + message);
    }
}
