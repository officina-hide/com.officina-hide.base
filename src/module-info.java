module com.officina_hide {
	requires java.sql;
	requires javafx.graphics;
	requires javafx.controls;
	requires java.desktop;
	requires javafx.base;
	
	opens com.officina_hide.workshop.graphics.basetest;
	opens com.officina_hide.workshop.task.tasklist;
}