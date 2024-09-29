/**
 * 
 */
module ExamApp {
	requires transitive javafx.graphics;
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.controls;
	requires java.sql;
	requires org.junit.jupiter.api;
	requires org.assertj.core;
	
	exports com.example;
	opens com.example to javafx.fxml;
	opens com.example.controllers.ui to javafx.fxml;
}