# SelfRx
因为`RxJava`类库庞大，学习起来不易理解，就一边阅读源码一边写的`RxJava 1.x`的简易版本，实现了`create`以及`map`和`subscribeOn`,仅供学习。
## map&subscribeOn
`map`和`subscribeOn`实现方式很类似,都是`create + OnSubscribe`,然后下游新创建的`Subscriber`作为代理转发上游传递的数据,详细的请看代码,虽然类很多,但是代码量很少,适合学习。
## todo  
继续阅读源码补充其他的操作符的实现
