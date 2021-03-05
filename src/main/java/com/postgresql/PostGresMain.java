package com.postgresql;

public class PostGresMain {
	public static void main(String[] args) {
		SingletonDesignPattern jdbc = SingletonDesignPattern.getInstance();
		try {
			jdbc.view("shanu");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

}
