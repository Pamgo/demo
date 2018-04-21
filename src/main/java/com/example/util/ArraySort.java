package com.example.util;

import java.util.Arrays;

/**
 * 使用Comparable接口：让待排序对象所在的类实现Comparable接口，并重写Comparable接口中的compareTo()方法
 * 缺点是只能按照一种规则排序
 * @author OKali
 *
 */
public class ArraySort {

	public static void main(String[] args) {
		Person[] persons = new Person[5];
		persons[0] = new Person("tom", 45);
		persons[1] = new Person("jack", 12);
		persons[2] = new Person("bill", 21);
		persons[3] = new Person("kandy", 34);
		persons[4] = new Person();
		Arrays.sort(persons);
		for (Person person : persons) {
			System.out.println(person);
		}
	}
}

class Person implements Comparable<Person> {

	private String name;

	private int age;

	public Person(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public Person() {
		this("unknow", 0);
	}

	// 重写该方法，使其按照从小到大排序
	@Override
	public int compareTo(Person o) {
		return age - o.age;
	}

	@Override
	public String toString() {
		return "Person[name:" + name + ",age:" + age + "]";
	}
}
