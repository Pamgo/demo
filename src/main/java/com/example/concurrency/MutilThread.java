package com.example.concurrency;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;

import com.sun.management.ThreadMXBean;

/**
 * 使用JMX来查看一个普通的java程序包含哪些线程
 * @author OKali
 *
 */
public class MutilThread {
	
	public static void main(String[] args) {
		// 获取java线程管理MXBean
		ThreadMXBean threadMXBean = (ThreadMXBean) ManagementFactory.getThreadMXBean();
		// 不需要获取同步的monitor和synchronizer信息，仅获取线程和线程堆栈信息
		ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
		for (ThreadInfo threadInfo : threadInfos) {
			System.out.println("[" + threadInfo.getThreadId()+"]"+threadInfo.getThreadName());
		}
	}

}
