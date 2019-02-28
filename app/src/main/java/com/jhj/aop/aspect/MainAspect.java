package com.jhj.aop.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

@Aspect
public class MainAspect {

    @Around("execution(@com.jhj.aop.annotation.Main void *(..))")
    public void doMain(final ProceedingJoinPoint joinPoint) {
        Observable
                .create(new ObservableOnSubscribe<Object>() {
                    @Override
                    public void subscribe(ObservableEmitter<Object> emitter) throws Exception {
                        try {
                            //执行真实方法
                            joinPoint.proceed();
                        } catch (Throwable throwable) {
                            throwable.printStackTrace();
                        }
                    }
                })
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe();

       /*

        joinPoint.getArgs(); //参数
        Object a = joinPoint.proceed(); //返回值*/
    }
}
