package com.startererp.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	private static final String URL =
		    "jdbc:mysql://localhost:3306/startererp?useSSL=false&serverTimezone=UTC&allowPublicKeyRetrieval=true";
    private static final String USER = "root";
    private static final String PASSWORD = "Vickybablu@2806";

    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // Temporary test
    public static void main(String[] args) {
        if (getConnection() != null) {
            System.out.println("✅ Database connected successfully");
        } else {
            System.out.println("❌ Database connection failed");
        }
    }
}
