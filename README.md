# AOP


```
“execution(@com.jhj.aop.annotation.Async void *(..))”
```

在Pointcut这里，使用了execution，也就是以方法执行时为切点，触发Aspect类。而execution里面的字符串是触发条件，也是具体的切点。“execution(@com.jhj.aop.annotation.Async void *(..))”这个条件是所有加了Async注解的方法都会是切点，范围比较广。

- com.jhj.aop 包名
- Async:Class
- void：表示返回值
- *：表示是任意方法名
- (..)：表示任意类型任意多个参数