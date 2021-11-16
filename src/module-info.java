module com.officina_hide {
	requires java.sql;
	requires javafx.graphics;
	requires javafx.controls;
	requires java.desktop;
	requires javafx.base;
	requires java.xml;
	
	opens com.officina_hide.fx.view;
	opens com.officina_hide.account.fx;
}