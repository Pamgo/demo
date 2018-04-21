package com.example.util;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 使用Comparator接口：编写多个排序方式类实现Comparator接口，并重写新Comparator接口中的compare()方法 public
 * static <t> void sort(T[] a,Comparator<!--? super T--> c),
 * 根据指定比较器产生的顺序对指定对象数组进行排序。数组中的所有元素都必须是通过指定比较器可相互比较的 （也就是说，对于数组中的任何 e1 和 e2
 * 元素而言，c.compare(e1, e2) 不得抛出 ClassCastException）。
 * 优点是可以按照多种方式排序，你要按照什么方式排序，就创建一个实现Comparator接口的排序方式类，
 * 然后将该排序类的对象传入到Arrays.sort(待排序对象，该排序方式类的对象)
 * 
 * @author OKali
 *
 */
public class ArraySort2 {
	public static void main(String[] args) {
		Student[] persons = new Student[5];
		persons[0] = new Student("tom", 1, 88, 45);
		persons[1] = new Student("jack", 6, 80, 12);
		persons[2] = new Student("bill", 4, 68, 21);
		persons[3] = new Student("kandy", 2, 98, 34);
		persons[4] = new Student("lily", 5, 94, 20);
		System.out.println("排序前的数据：");
		for (Student student : persons) {
			System.out.println(student);
		}
		// 创建SortByNumber对象，将其作为参数传入Arrays.sort(persons,sortByNumber)方法中
		SortByNumber sortByNumber = new SortByNumber();
		Arrays.sort(persons, sortByNumber);
		System.out.println("根据学生编号由低到高排序：");
		for (Student student : persons) {
			System.out.println(student);
		}
		SortByScore sortByScore = new SortByScore();
		Arrays.sort(persons, sortByScore);
		System.out.println("根据学生成绩由高到低排序：");
		for (Student student : persons) {
			System.out.println(student);
		}

	}

}

class Student {
	private String name;
	private int number;
	private int score;
	private int age;

	public Student(String name, int number, int score, int age) {
		this.name = name;
		this.number = number;
		this.score = score;
		this.age = age;
	}

	// 重写Student类的toString()方法，在输入对象时按照以下方式输出
	@Override
	public String toString() {
		return "Student[name:" + name + ",age:" + age + ",number:" + number + ",score:" + score + "]";
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

}

// 按照学号由低到高排列,创建SortByNumber类，该类实现Comparator，重写该接口的compare()
class SortByNumber implements Comparator<Student> {
	// 重写该接口的compare()使其按照学号由小到大排序（前者减去后者）
	@Override
	public int compare(Student o1, Student o2) {
		return o1.getNumber() - o2.getNumber();

	}

}

// 按照分数由高到低排列，创建SortByScore类，该类实现Comparator，重写该接口的compare()
class SortByScore implements Comparator<Student> {
	// 重写该接口的compare()使其按照分数由高到低排序（后者减去前者）
	@Override
	public int compare(Student o1, Student o2) {
		return o2.getScore() - o1.getScore();

	}

}