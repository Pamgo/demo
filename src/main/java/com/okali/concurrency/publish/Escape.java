package com.okali.concurrency.publish;

import com.annotation.NotRecommend;
import com.annotation.NotThreadSafe;

import lombok.extern.slf4j.Slf4j;

/**
 * 对象溢出
 * @author OKali
 * 对象溢出：一种错误的发布。当一个对象还没有构造完成时，就使它被其它线程锁见
 */
@Slf4j
@NotThreadSafe
@NotRecommend("不推荐写法")
public class Escape {
	
	private int thisCanBeEscape = 0;
	
	public Escape () {
		new InnerClass();
	}
	
	private class InnerClass {
		
		// 关键代码this
		public InnerClass() {
			log.info("{}", Escape.this.thisCanBeEscape);
		}
	}
	
	public static void main(String[] args) {
		new Escape();
	}
	
}
