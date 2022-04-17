module com.officina_hide {
	requires javafx.graphics;
	requires java.sql;
	requires javafx.controls;
	requires metadata.extractor;
	
	opens com.officina_hide.project_ad.fx;
	opens com.officina_hide.ui.view;
}