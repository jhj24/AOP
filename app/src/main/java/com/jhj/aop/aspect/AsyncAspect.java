package com.jhj.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.schedulers.Schedulers;

@Aspect
public class AsyncAspect {

    @Around("execution(@com.jhj.aop.annotation.Async void *(..))")
    public void doAsync(final ProceedingJoinPoint joinPoint) {
        Observable
                .create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                        try {
                            joinPoint.proceed();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe();

       /*

        joinPoint.getArgs(); //参数
        Object a = joinPoint.proceed(); //返回值*/
    }
}
