module com.officina_hide {
	requires java.sql;
	requires javafx.graphics;
	requires javafx.controls;
	
	opens com.officina_hide.workshop.graphics.basetest;
	opens com.officina_hide.workshop.task.tasklist;
}