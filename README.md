日常研究
==============
博客推荐【[java多线程系列](http://www.cnblogs.com/-new/p/7234332.html)  [分布式锁1](http://www.hollischuang.com/archives/1716)  [分布式锁2](http://61b4d741.wiz03.com/share/s/1xJdt12zWkRX2kf5WN2t3Sec07-aUC2Kpk1N2OU5o52SOHLD?)】

内容包含：

* jdk不常用源码类：
	> com.example.util

* 线程：
	> com.example.concurrency

* 设计模式：
	> com.example.pattern

* RPC：
	> com.example.rpc

* Spring源码原理案例等基础：
	> com.example.cache,com.okali.ioc

* 并发编程与高并发解决方案系统学习研究：
	> com.okali.concurrency
	
========================================================	

#####JVM的GC日志的主要参数
* -XX:+PrintGC 输出GC日志
* -XX:+PrintGCDetails 输出GC的详细日志
* -XX:+PrintGCTimeStamps 输出GC的时间戳（以基准时间的形式）
* -XX:+PrintGCDateStamps 输出GC的时间戳（以日期的形式，如 2013-05-04T21:53:59.234+0800）
* -XX:+PrintHeapAtGC 在进行GC的前后打印出堆的信息
* -XX:+PrintGCApplicationStoppedTime // 输出GC造成应用暂停的时间
* -Xloggc:../logs/gc.log 日志文件的输出路径
* -XX:+HeapDumpOnOutOfMemoryError //发生OOM的时候自动dump堆栈方便分析
