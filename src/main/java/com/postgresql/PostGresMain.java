package com.postgresql;

public class PostGresMain {

	public static void main(String[] args) {
		PostGresCrud connection = PostGresCrud.getInstance();
		PostGresCrud connection2 = PostGresCrud.getInstance();
		try {
			connection.insert(5, "shalini", 27, "Hassan", 09f);
			connection.view("shanu");
			connection.update("shanu", 35);
			connection.delete("shanu");

		} catch (Exception e) {
			System.out.println("Error is " + e);
		}
	}

}
