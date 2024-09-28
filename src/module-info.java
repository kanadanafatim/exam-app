/**
 * 
 */
module ExamApp {
	requires javafx.fxml;
	requires javafx.base;
	requires javafx.graphics;
	requires javafx.controls;
	requires java.sql;
	
	exports com.something;
	opens com.something to javafx.fxml;
	opens com.something.controllers.ui to javafx.fxml;
}