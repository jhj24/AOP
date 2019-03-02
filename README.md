# AOP

### AspectJ 术语

- Advice：需要被注入到.class字节码文件的代码。通常有三种：before，after和around，分别是在目标方法执行前，执行后以及替换目标代码执行。除了注入代码到方法中外，更进一步的，你还可以做一些别的修改，例如添加成员变量和接口到一个类中。
- Join point：程序中执行代码插入的点，例如方法调用时或者方法执行时。
- Pointcut：告诉代码注入工具在哪里并注入特定代码的表达式（即需要在哪些Joint point应用特定的Advice）。
- Aspect： Aspect将pointcut和advice 联系在一起。例如，我们通过定义一个pointcut和给出一个准确的advice实现向我们的程序中添加一个打印日志功能的aspect。




```
“execution(@com.jhj.aop.annotation.Async void *(..))”
```

在Pointcut这里，使用了execution，也就是以方法执行时为切点，触发Aspect类。而execution里面的字符串是触发条件，也是具体的切点。“execution(@com.jhj.aop.annotation.Async void *(..))”这个条件是所有加了Async注解的方法都会是切点，范围比较广。

- com.jhj.aop 包名
- Async:Class
- void：表示返回值
- *：表示是任意方法名
- (..)：表示任意类型任意多个参数


execution是在被切入的方法中，call是在调用被切入的方法前或者后
- 对于Call来说：
```
Call（Before）
Pointcut{
    Pointcut Method
}
Call（After）
```
- 对于Execution来说：
```
Pointcut{
  execution（Before）
    Pointcut Method
  execution（After）
}
```
